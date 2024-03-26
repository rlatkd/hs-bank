package exception.authentication;

import exception.BaseException;

public class AuthFailureException extends BaseException {
    public AuthFailureException() throws BaseException {
        super("         ___________________________________\n"
        	+ "         |                                 |\n"
        	+ "         |          인증에 실패했습니다          |\n"
        	+ "         |           다시 입력해주세요          |\n"
        	+ "         |_________________________________|\n");
    }
}