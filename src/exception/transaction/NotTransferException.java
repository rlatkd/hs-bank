package exception.transaction;

import exception.BaseException;

public class NotTransferException extends BaseException {
    public NotTransferException() {
        super("이체가 아닌 거래 유형입니다.");
    }

}
