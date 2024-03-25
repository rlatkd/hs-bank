package service;

import dto.transaction.request.GetTransactionDto;
import entity.Account;
import entity.Transaction;
import exception.*;
import repository.AccountRepository;
import repository.TransactionRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
    private static TransactionService transactionService;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private TransactionService() {
        this.transactionRepository = TransactionRepository.getInstance();
        this.accountRepository = AccountRepository.getInstance();
    }
    public static TransactionService getInstance(){
        if(transactionService == null)
            transactionService = new TransactionService();
        return transactionService;
    }

    public List<GetTransactionDto> getTransactionList(int accountId)
            throws DataAccessException, EmptyTransactionListException {

        List<Transaction> transactionList = transactionRepository.getTransactionList(accountId);
        if(transactionList.isEmpty()) throw new EmptyTransactionListException();

        return transactionList.stream()
                .map(transaction -> GetTransactionDto.toDto(transaction))
                .collect(Collectors.toList());
    }

    public synchronized void cancelTransaction(int id)
            throws DataAccessException, TransactionNotFoundException, AccountNotFoundException, TransactionTypeNotFoundException, DeactivateAccountException {

        Transaction transaction = transactionRepository.getTransaction(id);
        if(transaction == null) throw new TransactionNotFoundException();

        int withdrawAccountId = transaction.getWithdrawAccountId();
        int depositAccountId = transaction.getDepositAccountId();

        String type = transaction.getType();
        long amount = transaction.getAmount();

        Account depositAccount = accountRepository.getAccount(depositAccountId);
        Account withdrawAccount = accountRepository.getAccountWithoutLoad(withdrawAccountId);

        switch(type) {
            case "deposit":
                if(depositAccount == null) throw new AccountNotFoundException();
                if(!isActiveAccount(depositAccount)) throw new DeactivateAccountException();
                depositAccount.setBalance(depositAccount.getBalance() - amount);
                break;
            case "withdraw":
                if(withdrawAccount == null) throw new AccountNotFoundException();
                if(!isActiveAccount(withdrawAccount)) throw new DeactivateAccountException();
                withdrawAccount.setBalance(withdrawAccount.getBalance() + amount);
                break;
            case "transfer":
                if(depositAccount == null) throw new AccountNotFoundException("입금한 계좌가 존재하지 않습니다.");
                if(!isActiveAccount(depositAccount)) throw new DeactivateAccountException("입금한 계좌가 비활성화 상태입니다.");

                if(withdrawAccount == null) throw new AccountNotFoundException("출금한 계좌가 존재하지 않습니다.");
                if(!isActiveAccount(withdrawAccount)) throw new DeactivateAccountException("출금한 계좌가 비활성화 상태입니다.");
                if(!isActiveAccount(withdrawAccount)) throw new DeactivateAccountException();

                depositAccount.setBalance(depositAccount.getBalance() - amount);
                withdrawAccount.setBalance(withdrawAccount.getBalance() + amount);
                break;
            default:
                throw new TransactionTypeNotFoundException();
        }

        transaction.setStatus("cancel");

        accountRepository.update();
        transactionRepository.update();
    }

    protected boolean isActiveAccount(Account account) {
        return account.getStatus().equals("active") ? true : false;
    }
}
