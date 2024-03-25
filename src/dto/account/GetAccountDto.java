package dto.account;

import entity.Account;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAccountDto {
    private int id;
    private String bankName;
    private String number;
    private String ownerName;
    private long balance;
    private String registeredAt;
    private String status;

    public static GetAccountDto toDto(Account account, String ownerName){
        return GetAccountDto.builder().
                id(account.getId()).
                bankName(account.getBankName()).
                number(account.getNumber()).
                ownerName(ownerName).
                balance(account.getBalance()).
                registeredAt(account.getRegisteredAt()).
                status(account.getStatus()).
                build();
    }
    @Override
    public String toString() {
        return "GetAccountDto{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", number='" + number + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", registeredAt='" + registeredAt + '\'' +
                ", status='" + status + '\'' +
                '}' + '\n';
    }
}
