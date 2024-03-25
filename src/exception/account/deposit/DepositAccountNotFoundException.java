package exception.account.deposit;


import exception.BaseException;
import exception.account.AccountNotFoundException;

public class DepositAccountNotFoundException extends AccountNotFoundException {
    public DepositAccountNotFoundException() throws BaseException {
        super("입금할 계좌가 존재하지 않습니다.");
    }
}
