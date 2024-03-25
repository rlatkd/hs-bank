package exception.user;

import exception.BaseException;

public abstract class UserExistException extends BaseException {
    public UserExistException(String message) {
        super(message);
    }
}
