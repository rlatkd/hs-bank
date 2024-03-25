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
            throws AccountAlreadyExistsException, DataAccessException {

        if(accountRepository.isExist(registerAccountDto.getNumber()))
            throw new AccountAlreadyExistsException();

        accountRepository.add(registerAccountDto.toEntity());
    }

    public List<GetAccountDto> getAccountList(int ownerId)
            throws EmptyAccountListException, UserNotFoundException, DataAccessException {

        List<Account> accountList = accountRepository.getEntityList(ownerId);
        if(accountList.isEmpty()) throw new EmptyAccountListException();

        Client owner = clientRepository.get(ownerId);
        if(owner == null) throw new UserNotFoundException();

        return accountList.stream()
                .map(account -> GetAccountDto.toDto(account, owner.getName())) // ownerName을 설정해야 함
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeAccount(int id)
            throws AccountNotFoundException, DataAccessException {

        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();
        accountRepository.remove(id);
    }

    public synchronized void deposit(int id, long amount)
            throws DataAccessException, AccountNotFoundException, DeactivateAccountException {

        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();
        if(!isActiveAccount(account)) throw new DeactivateAccountException();

        Transaction transaction = Transaction.builder().
                date(dateTimeNow).
                type("deposit").
                amount(amount).
                depositAccountId(id).
                status("complete").
                build();

        account.setBalance(account.getBalance() + amount);
        accountRepository.update();

        transactionRepository.add(transaction);
    }

    public synchronized void withdraw(int id, long amount)
            throws AccountNotFoundException, BalanceInsufficientException, DataAccessException, DeactivateAccountException {

        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();
        if(!isActiveAccount(account)) throw new DeactivateAccountException();
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

        transactionRepository.add(transaction);
    }

    public synchronized void transfer(int withdrawAccountId, String depositAccountNumber, long amount)
            throws DataAccessException, AccountNotFoundException, BalanceInsufficientException, DeactivateAccountException {

        Account withdrawAccount = accountRepository.get(withdrawAccountId);
        if(withdrawAccount == null) throw new AccountNotFoundException("출금할 계좌가 존재하지 않습니다.");
        if(!isActiveAccount(withdrawAccount)) throw new DeactivateAccountException("출금할 계좌가 비활성화 상태입니다.");
        if(withdrawAccount.getBalance() < amount) throw new BalanceInsufficientException();

        Account depositAccount = accountRepository.getWithoutLoad(depositAccountNumber);
        if(depositAccount == null) throw new AccountNotFoundException("입금할 계좌가 존재하지 않습니다.");
        if(!isActiveAccount(withdrawAccount)) throw new DeactivateAccountException("입금할 계좌가 비활성화 상태입니다.");

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

        transactionRepository.add(transaction);
    }

    public void activateAccount(int id) throws DataAccessException, AccountNotFoundException {
        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();

        account.setStatus("active");
        accountRepository.update();
    }

    public void deactivateAccount(int id) throws DataAccessException, AccountNotFoundException {
        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();

        account.setStatus("deactive");
        accountRepository.update();
    }

    protected boolean isActiveAccount(Account account) {
        return account.getStatus().equals("active") ? true : false;
    }
}
