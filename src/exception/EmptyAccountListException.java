package exception;

public class EmptyAccountListException extends Exception {
    public EmptyAccountListException() {
        super();
    }

    public EmptyAccountListException(String message) {
        super(message);
    }
}
