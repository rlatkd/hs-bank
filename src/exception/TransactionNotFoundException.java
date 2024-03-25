package exception;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException() {
        super("존재하지 않는 거래입니다.");
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
