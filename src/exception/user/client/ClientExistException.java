package exception.user.client;

import exception.BaseException;

public class ClientExistException extends BaseException {
    public ClientExistException() throws BaseException {
        super("이미 존재하는 고객입니다.");
    }

}
