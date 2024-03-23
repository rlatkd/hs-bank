package service;

import dto.account.RegisterAccountDto;
import entity.Account;
import exception.AccountAlreadyExistsException;
import exception.DataLoadingException;
import exception.DataSavingException;
import repository.AccountRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.utils.dateTimeFormatter;

public class AccountService {
    private static AccountService accountService;
    private final AccountRepository accountRepository;
    private AccountService() {
        this.accountRepository = AccountRepository.getInstance();
    }
    public static AccountService getInstance(){
        if(accountService == null)
            accountService = new AccountService();
        return accountService;
    }

    public void registerAccount(RegisterAccountDto registerAccountDto) throws AccountAlreadyExistsException, DataLoadingException, DataSavingException {
        if(accountRepository.isExistByNumber(registerAccountDto.getNumber()))
            throw new AccountAlreadyExistsException();

        accountRepository.addAccount(registerAccountDto.toEntity());
    }
}
