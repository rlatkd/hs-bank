package exception.account.withdraw;

import exception.account.AccountDeactivateException;

public class WithdrawAccountDeactivateException extends AccountDeactivateException {
    public WithdrawAccountDeactivateException() {
        super("출금할 계좌가 비활성화 상태입니다.");
    }

    public WithdrawAccountDeactivateException(String message) {
        super(message);
    }
}
