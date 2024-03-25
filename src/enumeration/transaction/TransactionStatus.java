package enumeration.transaction;

import lombok.Getter;

@Getter
public enum TransactionStatus {

    COMPLETE("완료"),
    CANCEL("취소");

    private String korean;
    TransactionStatus(String korean){
        this.korean = korean;
    }
}
