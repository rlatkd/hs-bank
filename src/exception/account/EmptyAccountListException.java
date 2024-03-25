package exception.account;

import exception.BaseException;

public class EmptyAccountListException extends BaseException {
    public EmptyAccountListException() {
        super("계좌 목록이 존재하지 않습니다.");
    }

}
