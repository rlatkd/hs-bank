package dto.account;

import entity.Account;
import lombok.Builder;
import lombok.Getter;
import static utils.DateUtils.dateTimeNow;

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
                registeredAt(dateTimeNow).
                status("active").
                build();
    }
}
