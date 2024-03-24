package service;

import repository.TransactionRepository;

public class TransactionService {
    private static TransactionService transactionService;
    private final TransactionRepository transactionRepository;
    private TransactionService() {
        this.transactionRepository = TransactionRepository.getInstance();
    }
    public static TransactionService getInstance(){
        if(transactionService == null)
            transactionService = new TransactionService();
        return transactionService;
    }

}
