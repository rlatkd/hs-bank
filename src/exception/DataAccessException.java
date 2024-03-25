package exception;

public class DataAccessException extends Exception{
    public DataAccessException() {
    	super("데이터를 불러오지 못했습니다.");
    }

    public DataAccessException(String message) {
        super(message);
    }

}
