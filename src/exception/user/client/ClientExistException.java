package exception.user.client;

import exception.user.UserExistException;

public class ClientExistException extends UserExistException {
    public ClientExistException() {
        super("이미 존재하는 고객입니다.");
    }

}
