package entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Account extends Entity {
    private String bankName;
    private String number;
    private int ownerId;
    private long balance;
    private String registeredAt;
    private String status;

    public void setBalance(long balance){
        this.balance = balance;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
