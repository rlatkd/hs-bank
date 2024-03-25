package exception.account;

import exception.BaseException;

public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException() {
        super("계좌가 존재하지 않습니다.");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
