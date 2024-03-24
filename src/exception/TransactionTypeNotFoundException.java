package exception;

public class TransactionTypeNotFoundException extends Exception {
    public TransactionTypeNotFoundException() {
        super("존재하지 않는 거래 유형입니다.");
    }

    public TransactionTypeNotFoundException(String message) {
        super(message);
    }
}
