package exception.user.admin;

import exception.user.UserNotFoundException;

public class AdminNotFoundException extends UserNotFoundException {
    public AdminNotFoundException() {
        super("존재하지 않는 관리자입니다.");
    }

}
