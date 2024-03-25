package exception.account.deposit;

import exception.account.AccountDeactivateException;

public class DepositAccountDeactivateException extends AccountDeactivateException {
    public DepositAccountDeactivateException() {
        super("입급할 계좌가 비활성화 상태입니다.");
    }

    public DepositAccountDeactivateException(String message) {
        super(message);
    }
}
