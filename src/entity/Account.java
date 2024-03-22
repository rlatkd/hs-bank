package entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {
    private String number;
    private String holderId;
    private long balance;
    private String registeredAt;
    private String status;
}
