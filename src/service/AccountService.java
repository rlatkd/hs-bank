package service;

import dto.account.response.GetAccountDto;
import dto.account.request.RegisterAccountDto;
import entity.Account;
import entity.Client;
import exception.*;
import repository.AccountRepository;
import repository.ClientRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AccountService {
    private static AccountService accountService;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private AccountService() {
        this.accountRepository = AccountRepository.getInstance();
        this.clientRepository = ClientRepository.getInstance();
    }
    public static AccountService getInstance(){
        if(accountService == null)
            accountService = new AccountService();
        return accountService;
    }

    public void registerAccount(RegisterAccountDto registerAccountDto) throws AccountAlreadyExistsException, DataLoadingException, DataSavingException {
        if(accountRepository.isExist(registerAccountDto.getNumber()))
            throw new AccountAlreadyExistsException();

        accountRepository.addAccount(registerAccountDto.toEntity());
    }

    public ArrayList<GetAccountDto> getAccountList(int ownerId) throws DataLoadingException, EmptyAccountListException, ClientNotFoundException {
        ArrayList<Account> accountList = accountRepository.getAccountList(ownerId);
        if(accountList.isEmpty()) throw new EmptyAccountListException();

        Client owner = clientRepository.getClient(ownerId);
        if(owner == null) throw new ClientNotFoundException();

        return accountList.stream()
                .map(account -> GetAccountDto.toDto(account, owner.getName())) // ownerName을 설정해야 함
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
