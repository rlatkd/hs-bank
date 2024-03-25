package exception.inquiry;

import exception.BaseException;

public class EmptyInquiryListException extends BaseException {
    public EmptyInquiryListException() {
        super("문의 목록이 존재하지 않습니다.");
    }

}
