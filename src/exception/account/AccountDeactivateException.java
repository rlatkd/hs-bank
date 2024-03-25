package exception.account;

import exception.BaseException;

public class AccountDeactivateException extends BaseException {
    public AccountDeactivateException() throws BaseException {
        super("계좌가 비활성화 상태입니다.");
    }
    public AccountDeactivateException(String message) throws BaseException {
        super(message);
    }
}
