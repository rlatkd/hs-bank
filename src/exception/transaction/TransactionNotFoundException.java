package exception.transaction;

import exception.BaseException;

public class TransactionNotFoundException extends BaseException {
    public TransactionNotFoundException() {
        super("존재하지 않는 거래입니다.");
    }

}
