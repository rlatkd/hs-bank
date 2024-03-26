package service;

import dto.transaction.DepositDto;
import dto.transaction.GetTransactionDto;
import dto.transaction.TransferDto;
import dto.transaction.WithdrawDto;
import entity.Account;
import entity.Transaction;
import enumeration.ActivationStatus;
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
import lombok.With;
import repository.AccountRepository;
import repository.TransactionRepository;
import utils.DateTimeGenerator;

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
        if(transactionList.isEmpty()) throw new TransactionListEmptyException();

        return transactionList.stream()
                .map(transaction -> GetTransactionDto.toDto(transaction))
                .collect(Collectors.toList());
    }

    public synchronized void deposit(DepositDto depositDto) throws BaseException {

        Account account = accountRepository.get(depositDto.getAccountId(), depositDto.getOwnerId());
        if(account == null) throw new AccountNotFoundException();
        if(!isActiveAccount(account)) throw new AccountDeactivateException();

        Transaction transaction = depositDto.toEntity();

        account.setBalance(account.getBalance() + depositDto.getAmount());
        accountRepository.update();

        transactionRepository.add(transaction);
    }

    public synchronized void withdraw(WithdrawDto withdrawDto) throws BaseException {
        Account account = accountRepository.get(withdrawDto.getAccountId(), withdrawDto.getOwnerId());
        if(account == null) throw new AccountNotFoundException();
        if(!isActiveAccount(account)) throw new AccountDeactivateException();
        if(account.getBalance() < withdrawDto.getAmount()) throw new BalanceInsufficientException();

        Transaction transaction = withdrawDto.toEntity();

        account.setBalance(account.getBalance() - withdrawDto.getAmount());
        accountRepository.update();

        transactionRepository.add(transaction);
    }

    public synchronized void transfer(TransferDto transferDto) throws BaseException {
        Account withdrawAccount = accountRepository.get(transferDto.getWithdrawAccountId(), transferDto.getWithdrawAccountOwnerId());
        if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
        if(!isActiveAccount(withdrawAccount)) throw new WithdrawAccountDeactivateException();
        if(withdrawAccount.getBalance() < transferDto.getAmount()) throw new BalanceInsufficientException();

        Account depositAccount = accountRepository.getWithoutLoad(transferDto.getDepositAccountNumber());
        if(depositAccount == null) throw new DepositAccountNotFoundException();
        if(!isActiveAccount(depositAccount)) throw new DepositAccountDeactivateException();

        Transaction transaction = transferDto.toEntity(depositAccount.getId());

        withdrawAccount.setBalance(withdrawAccount.getBalance() - transferDto.getAmount());

        depositAccount.setBalance(depositAccount.getBalance() + transferDto.getAmount());
        accountRepository.update();

        transactionRepository.add(transaction);
    }

    public synchronized void cancelTransaction(int id) throws BaseException {
        Transaction transaction = transactionRepository.get(id);
        if(transaction == null) throw new TransactionNotFoundException();
        if(!(transaction.getType() == TransactionType.TRANSFER)) throw new NotTransferException();

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
        return account.getStatus() == ActivationStatus.ACTIVATE ? true : false;
    }
}
