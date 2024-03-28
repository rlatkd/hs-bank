package exception.transaction;

import exception.BaseException;

public class NotCompeleteTransactionException extends BaseException {
    public NotCompeleteTransactionException() throws BaseException {
        super("완료된 거래가 아닙니다.");
    }
}
