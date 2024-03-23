package exception;

public class ExistingUserException extends Exception {
    public ExistingUserException() {
		super();
	}

    public ExistingUserException(String message) {
        super(message);
    }
}
