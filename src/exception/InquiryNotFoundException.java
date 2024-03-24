package exception;

public class InquiryNotFoundException extends Exception {
    public InquiryNotFoundException() {
        super();
    }

    public InquiryNotFoundException(String message) {
        super(message);
    }
}
