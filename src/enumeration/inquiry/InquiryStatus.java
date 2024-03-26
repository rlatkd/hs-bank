package enumeration.inquiry;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum InquiryStatus implements Serializable {

    COMPLETE("완료"),
    REJECT("반려"),
    WAIT("대기");

    private String korean;
    InquiryStatus(String korean){
        this.korean = korean;
    }
}
