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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utils.utils.dateTimeFormatter;

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

    public void deposit(int id, long amount)
            throws AccountNotFoundException, DataLoadingException, DataSavingException {

        Account account = accountRepository.getAccount(id);
        if(account == null) throw new AccountNotFoundException();

        Transaction transaction = Transaction.builder().
                date((LocalDateTime.now()).format(dateTimeFormatter)).
                type("deposit").
                amount(amount).
                depositAccountId(id).
                status("complete").
                build();

        transactionRepository.addTransaction(transaction);
        account.setBalance(account.getBalance() + amount);
        accountRepository.update();
    }
}
