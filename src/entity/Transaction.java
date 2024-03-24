package entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Transaction implements Serializable {
    private int id;
    private String date;
    private String type;
    private long amount;
    private int withdrawAccountId;
    private int depositAccountId;
    private String status;

    public void setId(int id){
        this.id = id;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
