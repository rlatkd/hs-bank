package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public class Transaction extends Entity {
    private String date;
    private String type;
    private long amount;
    private int withdrawAccountId;
    private int depositAccountId;
    private String status;

    public void setStatus(String status){
        this.status = status;
    }

}
