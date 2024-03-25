package exception.transaction;

import exception.BaseException;

public class EmptyTransactionListException extends BaseException {
    public EmptyTransactionListException() {
        super("거래 내역이 존재하지 않습니다.");
    }

}
