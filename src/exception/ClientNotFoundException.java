package exception;

public class ClientNotFoundException extends Exception {

	  public ClientNotFoundException() {
		    super("존재하지 않는 고객입니다.");
	  }

    public ClientNotFoundException(String message) {
        super(message);
    }
}