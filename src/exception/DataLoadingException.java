package exception;

public class DataLoadingException extends Exception{
    public DataLoadingException() {
    	super("데이터를 불러오지 못했습니다.");
    }

    public DataLoadingException(String message) {
        super(message);
    }

}
