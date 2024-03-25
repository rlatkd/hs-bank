package repository;

import constants.FilePath;
import entity.Account;
import exception.BaseException;
import exception.DataAccessException;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository extends Repository<Account> {
    private static AccountRepository accountRepository;
    private AccountRepository() {
        super(FilePath.ACCOUNT_PATH);
    }

    public static AccountRepository getInstance(){
        if(accountRepository == null)
            accountRepository = new AccountRepository();
        return accountRepository;

    }

    public boolean isExist(String number) throws BaseException {
        load();
        for(Account account : entityList)
            if(account.getNumber().equals(number)) return true;
        return false;
    }

    public List<Account> getEntityList(int ownerId) throws BaseException {
        load();
        List<Account> accountList = new ArrayList<>();
        for(Account account : entityList)
            if(account.getOwnerId() == ownerId) accountList.add(account);
        return accountList;
    }

    public Account getWithoutLoad(int id) {
        for(Account account : entityList)
            if(account.getId() == id) return account;
        return null;
    }

    public Account getWithoutLoad(String number){
        for(Account account : entityList)
            if(account.getNumber().equals(number)) return account;
        return null;
    }
}
