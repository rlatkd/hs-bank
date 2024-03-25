package exception.account.withdraw;

import exception.BaseException;
import exception.account.AccountDeactivateException;

public class WithdrawAccountDeactivateException extends AccountDeactivateException {
    public WithdrawAccountDeactivateException() throws BaseException {
        super("출금할 계좌가 비활성화 상태입니다.");
    }
}
