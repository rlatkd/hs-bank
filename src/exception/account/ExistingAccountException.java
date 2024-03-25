package exception.account;

import exception.BaseException;

public class ExistingAccountException extends BaseException {
    public ExistingAccountException() {
        super("이미 존재하는 계좌입니다.");
    }

}
