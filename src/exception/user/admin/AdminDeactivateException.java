package exception.user.admin;

import exception.BaseException;

public class AdminDeactivateException extends BaseException {
    public AdminDeactivateException() throws BaseException {
        super("비활성화된 관리자입니다.");
    }
}
