package exception.user.admin;

import exception.BaseException;
import exception.user.UserNotFoundException;

public class AdminNotFoundException extends UserNotFoundException {
    public AdminNotFoundException() throws BaseException {
        super("존재하지 않는 관리자입니다.");
    }

}
