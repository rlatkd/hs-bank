package service;

import dto.account.response.GetAccountDto;
import dto.account.request.RegisterAccountDto;
import entity.Account;
import entity.Client;
import entity.Transaction;
import exception.*;
import repository.AccountRepository;
import repository.ClientRepository;
import repository.TransactionRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utils.DateUtils.dateTimeNow;

public class AccountService {
    private static AccountService accountService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;

    private AccountService() {
        this.accountRepository = AccountRepository.getInstance();
        this.transactionRepository = TransactionRepository.getInstance();
        this.clientRepository = ClientRepository.getInstance();
    }
    public static AccountService getInstance(){
        if(accountService == null)
            accountService = new AccountService();
        return accountService;
    }

    public void registerAccount(RegisterAccountDto registerAccountDto)
            throws AccountAlreadyExistsException, DataLoadingException, DataSavingException {

        if(accountRepository.isExist(registerAccountDto.getNumber()))
            throw new AccountAlreadyExistsException();

        accountRepository.addAccount(registerAccountDto.toEntity());
    }

    public List<GetAccountDto> getAccountList(int ownerId)
            throws EmptyAccountListException, ClientNotFoundException, DataLoadingException {

        List<Account> accountList = accountRepository.getAccountList(ownerId);
        if(accountList.isEmpty()) throw new EmptyAccountListException();

        Client owner = clientRepository.getClient(ownerId);
        if(owner == null) throw new ClientNotFoundException();

        return accountList.stream()
                .map(account -> GetAccountDto.toDto(account, owner.getName())) // ownerName을 설정해야 함
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeAccount(int id)
            throws AccountNotFoundException, DataLoadingException, DataSavingException {

        Account account = accountRepository.getAccount(id);
        if(account == null) throw new AccountNotFoundException();
        accountRepository.remove(id);
    }

    public synchronized void deposit(int id, long amount)
            throws AccountNotFoundException, DataLoadingException, DataSavingException {

        Account account = accountRepository.getAccount(id);
        if(account == null) throw new AccountNotFoundException();

        Transaction transaction = Transaction.builder().
                date(dateTimeNow).
                type("deposit").
                amount(amount).
                depositAccountId(id).
                status("complete").
                build();

        account.setBalance(account.getBalance() + amount);
        accountRepository.update();

        transactionRepository.addTransaction(transaction);
    }

    public synchronized void withdraw(int id, long amount)
            throws AccountNotFoundException, BalanceInsufficientException, DataLoadingException, DataSavingException {

        Account account = accountRepository.getAccount(id);
        if(account == null) throw new AccountNotFoundException();
        if(account.getBalance() < amount) throw new BalanceInsufficientException();

        Transaction transaction = Transaction.builder().
                date(dateTimeNow).
                type("withdraw").
                amount(amount).
                withdrawAccountId(id).
                status("complete").
                build();

        account.setBalance(account.getBalance() - amount);
        accountRepository.update();

        transactionRepository.addTransaction(transaction);
    }

    public synchronized void transfer(int withdrawAccountId, String depositAccountNumber, long amount)
            throws DataLoadingException, AccountNotFoundException, BalanceInsufficientException, DataSavingException {

        Account withdrawAccount = accountRepository.getAccount(withdrawAccountId);
        Account depositAccount = accountRepository.getAccountWithoutLoad(depositAccountNumber);

        if(withdrawAccount == null) throw new AccountNotFoundException("출금할 계좌가 존재하지 않습니다.");
        if(withdrawAccount.getBalance() < amount) throw new BalanceInsufficientException();

        if(depositAccount == null) throw new AccountNotFoundException("입금할 계좌가 존재하지 않습니다.");

        Transaction transaction = Transaction.builder().
                date(dateTimeNow).
                type("transfer").
                amount(amount).
                withdrawAccountId(withdrawAccountId).
                depositAccountId(depositAccount.getId()).
                status("complete").
                build();

        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);

        depositAccount.setBalance(depositAccount.getBalance() + amount);
        accountRepository.update();

        transactionRepository.addTransaction(transaction);
    }

    public void activateAccount(int id) throws DataLoadingException, AccountNotFoundException, DataSavingException {
        Account account = accountRepository.getAccount(id);
        if(account == null) throw new AccountNotFoundException();

        account.setStatus("activate");
        accountRepository.update();
    }

    public void deactivateAccount(int id) throws DataLoadingException, AccountNotFoundException, DataSavingException {
        Account account = accountRepository.getAccount(id);
        if(account == null) throw new AccountNotFoundException();

        account.setStatus("deactivate");
        accountRepository.update();
    }
}
