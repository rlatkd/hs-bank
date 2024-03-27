package dto.transaction;

import entity.Transaction;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetTransactionDto {
    private int id;
    private String date;
    private String type;
    private long amount;
    private String withdrawAccountNumber;
    private String depositAccountNumber;
    private String status;

    public static GetTransactionDto toDto(Transaction transaction, String withdrawAccountNumber, String depositAccountNumber){
        return GetTransactionDto.builder()
                .id(transaction.getId())
                .date(transaction.getDate())
                .type(transaction.getType().getKorean())
                .amount(transaction.getAmount())
                .withdrawAccountNumber(withdrawAccountNumber)
                .depositAccountNumber(depositAccountNumber)
                .status(transaction.getStatus().getKorean())
                .build();
    }

    @Override
    public String toString() {
        return "[거래ID : " + id + "] " + 
                "[거래일시 : " + date + "] " +
                "[거래유형 : " + type + "] " +
                "[금액 : " + amount + "] " +
                "[출금계좌번호 : " + withdrawAccountNumber + "] " +
                "[입금계좌번호 : " + depositAccountNumber + "] " +
                "[거래상태 : " + status + "] " +
                '\n';
    }
}
