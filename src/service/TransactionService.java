package service;

import dto.transaction.GetTransactionDto;
import entity.Account;
import entity.Transaction;
import enumeration.transaction.TransactionStatus;
import enumeration.transaction.TransactionType;
import exception.BaseException;
import exception.account.AccountNotFoundException;
import exception.account.BalanceInsufficientException;
import exception.account.AccountDeactivateException;
import exception.account.deposit.DepositAccountDeactivateException;
import exception.account.deposit.DepositAccountNotFoundException;
import exception.account.withdraw.WithdrawAccountDeactivateException;
import exception.account.withdraw.WithdrawAccountNotFoundException;
import exception.transaction.TransactionListEmptyException;
import exception.transaction.TransactionNotFoundException;
import exception.transaction.NotTransferException;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

import static utils.DateUtils.dateTimeNow;

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
        if(transactionList.isEmpty()) throw new TransactionListEmptyException();

        return transactionList.stream()
                .map(transaction -> GetTransactionDto.toDto(transaction))
                .collect(Collectors.toList());
    }

    public synchronized void deposit(int id, long amount) throws BaseException {

        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();
        if(!isActiveAccount(account)) throw new AccountDeactivateException();

        Transaction transaction = Transaction.builder().
                date(dateTimeNow).
                type(TransactionType.DEPOSIT).
                amount(amount).
                depositAccountId(id).
                status(TransactionStatus.COMPLETE).
                build();

        account.setBalance(account.getBalance() + amount);
        accountRepository.update();

        transactionRepository.add(transaction);
    }

    public synchronized void withdraw(int id, long amount) throws BaseException {
        Account account = accountRepository.get(id);
        if(account == null) throw new AccountNotFoundException();
        if(!isActiveAccount(account)) throw new AccountDeactivateException();
        if(account.getBalance() < amount) throw new BalanceInsufficientException();

        Transaction transaction = Transaction.builder().
                date(dateTimeNow).
                type(TransactionType.WITHDRAW).
                amount(amount).
                withdrawAccountId(id).
                status(TransactionStatus.COMPLETE).
                build();

        account.setBalance(account.getBalance() - amount);
        accountRepository.update();

        transactionRepository.add(transaction);
    }

    public synchronized void transfer(int withdrawAccountId, String depositAccountNumber, long amount) throws BaseException {
        Account withdrawAccount = accountRepository.get(withdrawAccountId);
        if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
        if(!isActiveAccount(withdrawAccount)) throw new WithdrawAccountDeactivateException();
        if(withdrawAccount.getBalance() < amount) throw new BalanceInsufficientException();

        Account depositAccount = accountRepository.getWithoutLoad(depositAccountNumber);
        if(depositAccount == null) throw new DepositAccountNotFoundException();
        if(!isActiveAccount(withdrawAccount)) throw new DepositAccountDeactivateException();

        Transaction transaction = Transaction.builder().
                date(dateTimeNow).
                type(TransactionType.TRANSFER).
                amount(amount).
                withdrawAccountId(withdrawAccountId).
                depositAccountId(depositAccount.getId()).
                status(TransactionStatus.COMPLETE).
                build();

        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);

        depositAccount.setBalance(depositAccount.getBalance() + amount);
        accountRepository.update();

        transactionRepository.add(transaction);
    }

    public synchronized void cancelTransaction(int id) throws BaseException {
        Transaction transaction = transactionRepository.get(id);
        if(transaction == null) throw new TransactionNotFoundException();
        if(!transaction.getType().equals("transfer")) throw new NotTransferException();

        long amount = transaction.getAmount();
        int withdrawAccountId = transaction.getDepositAccountId();
        int depositAccountId = transaction.getWithdrawAccountId();

        Account withdrawAccount = accountRepository.get(withdrawAccountId);
        if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
        if(!isActiveAccount(withdrawAccount)) throw new WithdrawAccountDeactivateException();
        if(withdrawAccount.getBalance() < amount) throw new BalanceInsufficientException();

        Account depositAccount = accountRepository.getWithoutLoad(depositAccountId);
        if(depositAccount == null) throw new DepositAccountNotFoundException();
        if(!isActiveAccount(depositAccount)) throw new DepositAccountDeactivateException();

        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);
        depositAccount.setBalance(depositAccount.getBalance() + amount);

        transaction.setStatus(TransactionStatus.CANCEL);

        accountRepository.update();
        transactionRepository.update();
    }

    protected boolean isActiveAccount(Account account) {
        return account.getStatus().equals("active") ? true : false;
    }
}
