package exception;

public class EmptyAccountListException extends Exception {
    public EmptyAccountListException() {
        super("계좌 목록이 존재하지 않습니다.");
    }

    public EmptyAccountListException(String message) {
        super(message);
    }
}
