package entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Transaction {
    private int id;
    private String date;
    private String type;
    private long amount;
    private String withDrawAccountNumber;
    private String depositAccountNumber;
    private String status;

}
