package entity;

import enumeration.ActivationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public class Account extends Entity {
    private String bankName;
    private String number;
    private int ownerId;
    private long balance;
    private String registeredAt;
    private ActivationStatus status;

    public void setBalance(long balance){
        this.balance = balance;
    }

    public void setStatus(ActivationStatus status){
        this.status = status;
    }

}
