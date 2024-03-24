package exception;

public class DataSavingException extends Exception{
    public DataSavingException() {
    	super("데이터를 저장하지 못했습니다.");
    }

    public DataSavingException(String message) {
        super(message);
    }

}
