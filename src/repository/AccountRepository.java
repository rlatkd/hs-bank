package repository;

import entity.Account;
import exception.DataLoadingException;
import exception.DataSavingException;

import java.util.ArrayList;

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

    public boolean isExist(String number) throws DataLoadingException {
        load();
        for(Account account : dataList)
            if(account.getNumber().equals(number)) return true;
        return false;
    }

    public int getLastId(){
        return dataList.isEmpty() ? 0 : dataList.get(dataList.size() - 1).getId();
    }

    public void addAccount(Account account) throws DataLoadingException, DataSavingException {
        load();
        account.setId(getLastId() + 1);
        dataList.add(account);
        save();
    }

    public ArrayList<Account> getAccountList(int ownerId) throws DataLoadingException {
        load();

        ArrayList<Account> accountList = new ArrayList<>();
        for(Account account : dataList)
            if(account.getOwnerId() == ownerId) accountList.add(account);

        return accountList;
    }
}
