package exception.user.admin;

import exception.BaseException;

public class AdminExistException extends BaseException {
    public AdminExistException() throws BaseException {
        super("이미 존재하는 관리자입니다.");
    }

}
