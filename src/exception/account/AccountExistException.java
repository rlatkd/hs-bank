package exception.account;

import exception.BaseException;

public class AccountExistException extends BaseException {
    public AccountExistException() {
        super("이미 존재하는 계좌입니다.");
    }

}
