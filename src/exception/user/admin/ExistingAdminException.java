package exception.user.admin;

import exception.user.ExistingUserException;

public class ExistingAdminException extends ExistingUserException {
    public ExistingAdminException() {
        super("이미 존재하는 관리자입니다.");
    }

}
