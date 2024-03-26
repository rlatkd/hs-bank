package exception.user.client;

import exception.BaseException;

public class ClientDeactivateException extends BaseException {
    public ClientDeactivateException() throws BaseException {
        super("비활성화된 고객입니다.");
    }
}
