package enumeration.inquiry;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum InquiryCategory implements Serializable {

    ACCOUNT("계좌"),
    TRANSACTION("거래");

    private String korean;
    private InquiryCategory(String korean){
        this.korean = korean;
    }
}
