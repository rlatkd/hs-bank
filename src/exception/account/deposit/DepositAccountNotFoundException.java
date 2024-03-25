package exception.account.deposit;


import exception.account.AccountNotFoundException;

public class DepositAccountNotFoundException extends AccountNotFoundException {
    public DepositAccountNotFoundException() {
        super("입금 계좌가 존재하지 않습니다.");
    }

    public DepositAccountNotFoundException(String message) {
        super(message);
    }
}
