package exception.user.admin;

import exception.BaseException;

public class AdminNotFoundException extends BaseException {
    public AdminNotFoundException() throws BaseException {
        super("존재하지 않는 관리자입니다.");
    }

}
