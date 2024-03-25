package exception.user.admin;

import exception.user.UserExistException;

public class AdminExistException extends UserExistException {
    public AdminExistException() {
        super("이미 존재하는 관리자입니다.");
    }

}
