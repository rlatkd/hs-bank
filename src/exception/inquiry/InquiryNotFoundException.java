package exception.inquiry;

import exception.BaseException;

public class InquiryNotFoundException extends BaseException {
    public InquiryNotFoundException() {
        super("존재하지 않는 문의입니다.");
    }

}
