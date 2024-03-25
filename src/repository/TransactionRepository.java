package repository;

import entity.Transaction;
import exception.DataAccessException;

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

    public List<Transaction> getEntityList(int accountId) throws DataAccessException {
        load();
        List<Transaction> transactionList = new ArrayList<>();
        for(Transaction transaction : entityList)
            if(transaction.getDepositAccountId() == accountId
                    && transaction.getWithdrawAccountId() == accountId)
                transactionList.add(transaction);

        return entityList;
    }
}
