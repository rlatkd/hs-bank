package service;

import dto.transaction.GetTransactionDto;
import entity.Account;
import entity.Transaction;
import exception.BaseException;
import exception.account.AccountNotFoundException;
import exception.account.BalanceInsufficientException;
import exception.account.deposit.DeactiveDepositAccountException;
import exception.account.deposit.DepositAccountNotFoundException;
import exception.account.withdraw.DeactiveWithdrawAccountException;
import exception.account.withdraw.WithdrawAccountNotFoundException;
import exception.DataAccessException;
import exception.account.DeactiveAccountException;
import exception.transaction.EmptyTransactionListException;
import exception.transaction.TransactionNotFoundException;
import exception.transaction.TransactionTypeNotFoundException;
import repository.AccountRepository;
import repository.TransactionRepository;

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

    public List<GetTransactionDto> getTransactionList(int accountId) throws BaseException {
        List<Transaction> transactionList = transactionRepository.getEntityList(accountId);
        if(transactionList.isEmpty()) throw new EmptyTransactionListException();

        return transactionList.stream()
                .map(transaction -> GetTransactionDto.toDto(transaction))
                .collect(Collectors.toList());
    }

    public synchronized void cancelTransaction(int id) throws BaseException {
        Transaction transaction = transactionRepository.get(id);
        if(transaction == null) throw new TransactionNotFoundException();

        int withdrawAccountId = transaction.getWithdrawAccountId();
        int depositAccountId = transaction.getDepositAccountId();

        String type = transaction.getType();
        long amount = transaction.getAmount();

        Account depositAccount = accountRepository.get(depositAccountId);
        Account withdrawAccount = accountRepository.getWithoutLoad(withdrawAccountId);

        switch(type) {
            case "deposit":
                if(depositAccount == null) throw new DepositAccountNotFoundException();
                if(!isActiveAccount(depositAccount)) throw new DeactiveDepositAccountException();
                if(depositAccount.getBalance() < amount) throw new BalanceInsufficientException();
                depositAccount.setBalance(depositAccount.getBalance() - amount);
                break;
            case "withdraw":
                if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
                if(!isActiveAccount(withdrawAccount)) throw new DeactiveWithdrawAccountException();
                withdrawAccount.setBalance(withdrawAccount.getBalance() + amount);
                break;
            case "transfer":
                if(depositAccount == null) throw new DepositAccountNotFoundException();
                if(!isActiveAccount(depositAccount)) throw new DeactiveDepositAccountException();

                if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
                if(!isActiveAccount(withdrawAccount)) throw new DeactiveWithdrawAccountException();

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
