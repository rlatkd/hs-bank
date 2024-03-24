package exception;

public class EmptyTransactionListException extends Exception {
    public EmptyTransactionListException() {
        super("거래 내역이 존재하지 않습니다.");
    }

    public EmptyTransactionListException(String message) {
        super(message);
    }
}
