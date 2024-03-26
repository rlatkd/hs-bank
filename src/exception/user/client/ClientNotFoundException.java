package exception.user.client;

import exception.BaseException;

public class ClientNotFoundException extends BaseException {
    public ClientNotFoundException() throws BaseException {
        super("존재하지 않는 고객입니다.");
    }

}
