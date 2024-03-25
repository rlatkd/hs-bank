package repository;

import utils.FilePathConstants;
import entity.Transaction;
import exception.BaseException;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository extends Repository<Transaction>{
    private static TransactionRepository transactionRepository;
    private TransactionRepository() {
        super(FilePathConstants.TRANSACTION_PATH);
    }

    public static TransactionRepository getInstance(){
        if(transactionRepository == null)
            transactionRepository = new TransactionRepository();
        return transactionRepository;
    }

    public List<Transaction> getEntityList(int accountId) throws BaseException {
        load();
        List<Transaction> transactionList = new ArrayList<>();
        for(Transaction transaction : entityList)
            if(transaction.getDepositAccountId() == accountId
                    && transaction.getWithdrawAccountId() == accountId)
                transactionList.add(transaction);

        return entityList;
    }
}
