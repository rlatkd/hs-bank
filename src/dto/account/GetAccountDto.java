package dto.account;

import entity.Account;
import enumeration.ActivationStatus;
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
                status(account.getStatus().getKorean()).
                build();
    }
    @Override
    public String toString() {
        return "[계좌ID : " + id + "] " +
                "[은행 : " + bankName + "] " +
                "[계좌번호 : " + number + "] " +
                "[소유주 : " + ownerName + "] " +
                "[잔액(원) : " + balance + "] " +
                "[등록일시 : " + registeredAt + "] " +
                "[상태 : " + status + "] " +
                "\n";
    }
}
