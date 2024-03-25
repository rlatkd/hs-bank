package exception;

public class DataAccessException extends BaseException{
    public DataAccessException() throws BaseException {
    	super("시스템 오류로 데이터에 접근하지 못했습니다. 다시 시도해주세요.");
    }

}
