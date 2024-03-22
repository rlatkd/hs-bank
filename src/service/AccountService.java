package service;

import repository.AccountRepository;

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
}
