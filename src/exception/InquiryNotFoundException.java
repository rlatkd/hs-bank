package exception;

public class InquiryNotFoundException extends Exception {
    public InquiryNotFoundException() {
        super("존재하지 않는 문의입니다.");
    }

    public InquiryNotFoundException(String message) {
        super(message);
    }
}
