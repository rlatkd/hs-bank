package exception.verification;

import exception.BaseException;

public class AuthFailureException extends BaseException {
    public AuthFailureException() throws BaseException {
        super("\n인증번호가 틀립니다. 다시 입력해주세요.\n");
    }
}