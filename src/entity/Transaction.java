package entity;

import enumeration.ActivationStatus;
import enumeration.transaction.TransactionStatus;
import enumeration.transaction.TransactionType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Transaction extends Entity {
    private String date;
    private TransactionType type;
    private long amount;
    private int withdrawAccountId;
    private int depositAccountId;
    private TransactionStatus status;

    public void setStatus(TransactionStatus status){
        this.status = status;
    }

}
