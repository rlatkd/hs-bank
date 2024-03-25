package exception.account.withdraw;

import exception.account.DeactiveAccountException;

public class DeactiveWithdrawAccountException extends DeactiveAccountException {
    public DeactiveWithdrawAccountException() {
        super("출금 계좌가 비활성화 상태입니다.");
    }

    public DeactiveWithdrawAccountException(String message) {
        super(message);
    }
}
