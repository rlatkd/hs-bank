package enumeration.transaction;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum TransactionType implements Serializable {

    DEPOSIT("입금"),
    WITHDRAW("출금"),
    TRANSFER("이체");

    private String korean;
    private TransactionType(String korean){
        this.korean = korean;
    }
}
