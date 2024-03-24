package service;

import dto.transaction.request.GetTransactionDto;
import entity.Transaction;
import exception.DataLoadingException;
import exception.EmptyAccountListException;
import exception.EmptyTransactionListException;
import repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<GetTransactionDto> getTransactionList(int accountId)
            throws DataLoadingException, EmptyTransactionListException {

        List<Transaction> transactionList = transactionRepository.getTransactionList(accountId);
        if(transactionList.isEmpty()) throw new EmptyTransactionListException();

        return transactionList.stream()
                .map(transaction -> GetTransactionDto.toDto(transaction))
                .collect(Collectors.toList());
    }

}
