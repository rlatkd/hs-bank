package exception.account.deposit;

import exception.account.DeactiveAccountException;

public class DeactiveDepositAccountException extends DeactiveAccountException {
    public DeactiveDepositAccountException() {
        super("입급할 계좌가 비활성화 상태입니다.");
    }

    public DeactiveDepositAccountException(String message) {
        super(message);
    }
}
