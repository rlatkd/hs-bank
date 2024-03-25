package repository;

import entity.Account;
import exception.DataAccessException;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isExist(String number) throws DataAccessException {
        load();
        for(Account account : dataList)
            if(account.getNumber().equals(number)) return true;
        return false;
    }

    private int getLastId(){
        return dataList.isEmpty() ? 0 : dataList.get(dataList.size() - 1).getId();
    }

    public void addAccount(Account account) throws DataAccessException {
        load();
        account.setId(getLastId() + 1);
        dataList.add(account);
        save();
    }

    public List<Account> getAccountList(int ownerId) throws DataAccessException {
        load();

        List<Account> accountList = new ArrayList<>();
        for(Account account : dataList)
            if(account.getOwnerId() == ownerId) accountList.add(account);

        return accountList;
    }

    public Account getAccount(int id) throws DataAccessException {
        load();

        for(Account account : dataList)
            if(account.getId() == id) return account;

        return null;
    }

    public Account getAccountWithoutLoad(String number){
        for(Account account : dataList)
            if(account.getNumber().equals(number)) return account;
        return null;
    }

    public Account getAccount(String number) throws DataAccessException {
        load();

        for(Account account : dataList)
            if(account.getNumber().equals(number)) return account;

        return null;
    }

    public void remove(int id) throws DataAccessException {
        load();
        for(int i = 0; i < dataList.size(); i++){
            if(dataList.get(i).getId() == id) dataList.remove(i);
            break;
        }
        save();
    }

    public void update() throws DataAccessException {
        save();
    }

    public Account getAccountWithoutLoad(int id) {
        for(Account account : dataList)
            if(account.getId() == id) return account;

        return null;
    }
}
