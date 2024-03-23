package repository;

import entity.Account;

public class AccountRepository extends Repository<Account> {
    private static AccountRepository accountRepository;
    private AccountRepository() {
        super();
        this.path += "Account.txt";
    }

    public static AccountRepository getInstance(){
        if(accountRepository == null)
            accountRepository = new AccountRepository();
        return accountRepository;

    }

}
