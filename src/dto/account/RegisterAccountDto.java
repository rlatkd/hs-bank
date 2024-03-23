package dto.account;

import entity.Account;
import entity.Client;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static utils.utils.dateTimeFormatter;

@Builder
@Getter
public class RegisterAccountDto {
    private String bankName;
    private String number;
    private int ownerId;

    public Account toEntity(){
        return Account.builder().
                bankName(bankName).
                number(number).
                ownerId(ownerId).
                balance(0).
                registeredAt((LocalDateTime.now()).format(dateTimeFormatter)).
                status("activate").
                build();
    }
}
