package exception;

public class BalanceInsufficientException extends Exception {
    public BalanceInsufficientException() {
        super("잔액이 부족합니다.");
    }

    public BalanceInsufficientException(String message) {
        super(message);
    }
}
