package service;

import dto.account.GetAccountDto;
import dto.account.RegisterAccountDto;
import dto.account.RemoveAccountDto;
import entity.Account;
import entity.Client;
import enumeration.ActivationStatus;
import exception.BaseException;
import exception.account.AccountExistException;
import exception.account.AccountNotFoundException;
import exception.account.AccountListEmptyException;
import exception.user.client.ClientNotFoundException;
import repository.AccountRepository;
import repository.ClientRepository;
import repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new AccountExistException();

        accountRepository.add(registerAccountDto.toEntity());
    }

    public List<GetAccountDto> getAccountList(int ownerId) throws BaseException {
        List<Account> accountList = accountRepository.getEntityList(ownerId);
        if(accountList.isEmpty()) throw new AccountListEmptyException();

        Client owner = clientRepository.get(ownerId);
        if(owner == null) throw new ClientNotFoundException();

        return accountList.stream()
                .map(account -> GetAccountDto.toDto(account, owner.getName())) // ownerName을 설정해야 함
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeAccount(RemoveAccountDto removeAccountDto) throws BaseException {
        Account account = accountRepository.get(removeAccountDto.getId(), removeAccountDto.getOwnerId());
        if(account == null) throw new AccountNotFoundException();
        accountRepository.remove(removeAccountDto.getId());
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
