package exception.account.deposit;

import exception.BaseException;
import exception.account.AccountDeactivateException;

public class DepositAccountDeactivateException extends AccountDeactivateException {
    public DepositAccountDeactivateException() throws BaseException {
        super("입급할 계좌가 비활성화 상태입니다.");
    }
}
