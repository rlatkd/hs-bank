package exception.regex;
import exception.BaseException;

public class RegexNotValidException extends BaseException {
    public RegexNotValidException() throws BaseException {
        super("잘못된 입력입니다. 다시 입력해 주세요.\n");
    }
}
