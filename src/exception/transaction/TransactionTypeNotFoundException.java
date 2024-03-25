package exception.transaction;

import exception.BaseException;

public class TransactionTypeNotFoundException extends BaseException {
    public TransactionTypeNotFoundException() {
        super("존재하지 않는 거래 유형입니다.");
    }

}
