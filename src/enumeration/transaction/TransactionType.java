package enumeration.transaction;

import lombok.Getter;

@Getter
public enum TransactionType {

    DEPOSIT("입금"),
    WITHDRAW("출금"),
    TRANSFER("이체");

    private String korean;
    TransactionType(String korean){
        this.korean = korean;
    }
}
