package exception.view;
import exception.BaseException;

public class InputNotCorrectException extends BaseException {
    public InputNotCorrectException() throws BaseException {
        super("잘못된 입력입니다. 다시 입력해 주세요.\n");
    }
}
