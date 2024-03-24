package repository;

import entity.Transaction;
import exception.DataLoadingException;
import exception.DataSavingException;

import java.io.FileNotFoundException;

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
}
