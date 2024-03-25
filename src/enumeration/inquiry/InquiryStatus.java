package enumeration.inquiry;

import lombok.Getter;

@Getter
public enum InquiryStatus {

    COMPLETE("완료"),
    REJECT("반려"),
    WAIT("대기");

    private String korean;
    InquiryStatus(String korean){
        this.korean = korean;
    }
}
