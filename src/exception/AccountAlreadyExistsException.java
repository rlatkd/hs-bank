package exception;

public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException() {
        super("이미 존재하는 계좌입니다.");
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}
