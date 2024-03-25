package exception.account;

import exception.BaseException;

public class BalanceInsufficientException extends BaseException {
    public BalanceInsufficientException() throws BaseException {
        super("잔액이 부족합니다.");
    }

}
