package exception.inquiry;

import exception.BaseException;

public class InquiryListEmptyException extends BaseException {
    public InquiryListEmptyException() throws BaseException {
        super("문의 목록이 존재하지 않습니다.");
    }

}
