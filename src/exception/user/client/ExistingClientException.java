package exception.user.client;

import exception.user.ExistingUserException;

public class ExistingClientException extends ExistingUserException {
    public ExistingClientException() {
        super("이미 존재하는 고객입니다.");
    }

}
