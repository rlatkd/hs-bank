package exception.user;

import exception.BaseException;

public abstract class ExistingUserException extends BaseException {
    public ExistingUserException(String message) {
        super(message);
    }
}
