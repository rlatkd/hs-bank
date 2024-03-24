package exception;

public class EmptyInquiryListException extends Exception {
    public EmptyInquiryListException() {
        super();
    }

    public EmptyInquiryListException(String message) {
        super(message);
    }
}
