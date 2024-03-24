package exception;

public class ExistingUserException extends Exception {
    public ExistingUserException() {
		super("이미 존재하는 사용자입니다.");
	}

    public ExistingUserException(String message) {
        super(message);
    }
}
