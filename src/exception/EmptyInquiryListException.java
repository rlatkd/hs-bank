package exception;

public class EmptyInquiryListException extends Exception {
    public EmptyInquiryListException() {
        super("문의 목록이 존재하지 않습니다.");
    }

    public EmptyInquiryListException(String message) {
        super(message);
    }
}
