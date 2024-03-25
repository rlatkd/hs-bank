package enumeration.transaction;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum TransactionStatus implements Serializable {

    COMPLETE("완료"),
    CANCEL("취소");

    private String korean;
    TransactionStatus(String korean){
        this.korean = korean;
    }
}
