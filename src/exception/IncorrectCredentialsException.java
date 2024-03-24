package exception;

public class IncorrectCredentialsException extends Exception {
	public IncorrectCredentialsException() {
		super("존재하지 않는 사용자입니다.");
	}
	
	public IncorrectCredentialsException(String message) {
		super(message);
	}
}
