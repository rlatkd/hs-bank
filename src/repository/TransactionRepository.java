package repository;

import entity.Transaction;

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
}
