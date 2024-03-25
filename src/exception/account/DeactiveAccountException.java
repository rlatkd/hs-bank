package exception.account;

import exception.BaseException;

public class DeactiveAccountException extends BaseException {
    public DeactiveAccountException() {
        super("계좌가 비활성화 상태입니다.");
    }
    public DeactiveAccountException(String message) {
        super(message);
    }
}
