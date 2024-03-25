package exception;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("존재하지 않는 사용자입니다.");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
}
