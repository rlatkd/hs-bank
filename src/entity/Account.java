package entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Account implements Serializable {
    private int id;
    private String number;
    private String holderId;
    private long balance;
    private String registeredAt;
    private String status;

}
