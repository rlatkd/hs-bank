package exception;

public class DeactivateAccountException extends Exception {
    public DeactivateAccountException() {
        super("비활성화된 계좌입니다.");
    }

    public DeactivateAccountException(String message) {
        super(message);
    }
}
