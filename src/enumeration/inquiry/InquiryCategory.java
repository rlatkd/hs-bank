package enumeration.inquiry;

import lombok.Getter;

@Getter
public enum InquiryCategory {

    ACCOUNT("계좌"),
    TRANSACTION("거래");

    private String korean;
    InquiryCategory(String korean){
        this.korean = korean;
    }
}
