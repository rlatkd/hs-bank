package exception.account.withdraw;


import exception.account.AccountNotFoundException;

public class WithdrawAccountNotFoundException extends AccountNotFoundException {
    public WithdrawAccountNotFoundException() {
        super("출금할 계좌가 존재하지 않습니다.");
    }

}
