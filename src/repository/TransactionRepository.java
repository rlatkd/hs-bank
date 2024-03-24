package repository;

import entity.Transaction;
import exception.DataLoadingException;
import exception.DataSavingException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository extends Repository<Transaction>{
    private static TransactionRepository transactionRepository;
    private TransactionRepository() {
        super();
        this.path += "Transaction.txt";
    }

    public static TransactionRepository getInstance(){
        if(transactionRepository == null)
            transactionRepository = new TransactionRepository();
        return transactionRepository;
    }

    public void addTransaction(Transaction transaction) throws DataLoadingException, DataSavingException {
        load();
        dataList.add(transaction);
        save();
    }

    public void update() throws DataSavingException {
        save();
    }

    public List<Transaction> getTransactionList(int accountId) throws DataLoadingException {
        load();
        List<Transaction> transactionList = new ArrayList<>();
        for(Transaction transaction : dataList)
            if(transaction.getDepositAccountId() == accountId
                    && transaction.getWithdrawAccountId() == accountId)
                transactionList.add(transaction);

        return dataList;
    }
}
