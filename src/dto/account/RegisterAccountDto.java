package dto.account;

import entity.Account;
import enumeration.ActivationStatus;
import lombok.Builder;
import lombok.Getter;
import static utils.DateTimeGenerator.getDateTimeNow;

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
                registeredAt(getDateTimeNow()).
                status(ActivationStatus.ACTIVATE).
                build();
    }
}
