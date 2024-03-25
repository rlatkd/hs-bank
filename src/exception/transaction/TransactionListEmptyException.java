package exception.transaction;

import exception.BaseException;

public class TransactionListEmptyException extends BaseException {
    public TransactionListEmptyException() throws BaseException {
        super("거래 내역이 존재하지 않습니다.");
    }

}
