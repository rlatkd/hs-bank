package service;

import dto.account.GetAccountDto;
import dto.account.RegisterAccountDto;
import entity.Account;
import entity.Client;
import entity.Transaction;
import enumeration.ActivationStatus;
import exception.BaseException;
import exception.account.ExistingAccountException;
import exception.account.AccountNotFoundException;
import exception.account.BalanceInsufficientException;
import exception.account.EmptyAccountListException;
import exception.account.deposit.DeactiveDepositAccountException;
import exception.account.deposit.DepositAccountNotFoundException;
import exception.account.withdraw.DeactiveWithdrawAccountException;
import exception.account.withdraw.WithdrawAccountNotFoundException;
import exception.DataAccessException;
import exception.account.DeactiveAccountException;
import exception.user.client.ClientNotFoundException;
import repository.AccountRepository;
import repository.ClientRepository;
import repository.TransactionRepository;

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

    public void registerAccount(RegisterAccountDto registerAccountDto) throws BaseException {
        if(accountRepository.isExist(registerAccountDto.getNumber()))
            throw new ExistingAccountException();

        accountRepository.add(registerAccountDto.toEntity());
    }

    public List<GetAccountDto> getAccountList(int ownerId) throws BaseException {
        List<Account> accountList = accountRepository.getEntityList(ownerId);
        if(accountList.isEmpty()) throw new EmptyAccountListException();

        Client owner = clientRepository.get(ownerId);
        if(owner == null) throw new ClientNotFoundException();

        return accountList.stream()
                .map(account -> GetAccountDto.toDto(account, owner.getName())) // ownerName을 설정해야 함
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeAccount(int id) throws BaseException {
        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();
        accountRepository.remove(id);
    }

    public void activateAccount(int id) throws BaseException {
        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();

        account.setStatus(ActivationStatus.ACTIVATE);
        accountRepository.update();
    }

    public void deactivateAccount(int id) throws BaseException {
        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();

        account.setStatus(ActivationStatus.DEACTIVATE);
        accountRepository.update();
    }
}
