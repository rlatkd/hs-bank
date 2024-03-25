package entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
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
