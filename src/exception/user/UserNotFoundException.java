package exception.user;

import exception.BaseException;

public abstract class UserNotFoundException extends BaseException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
