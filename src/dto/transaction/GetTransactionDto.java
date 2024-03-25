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
    private int withdrawAccountNumber;
    private int depositAccountNumber;
    private String status;

    public static GetTransactionDto toDto(Transaction transaction){
        return GetTransactionDto.builder()
                .id(transaction.getId())
                .date(transaction.getDate())
                .type(transaction.getType().getKorean())
                .amount(transaction.getAmount())
                .withdrawAccountNumber(transaction.getWithdrawAccountId())
                .depositAccountNumber(transaction.getDepositAccountId())
                .status(transaction.getStatus().getKorean())
                .build();
    }

    @Override
    public String toString() {
        return "GetTransactionDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", withdrawAccountNumber=" + withdrawAccountNumber +
                ", depositAccountNumber=" + depositAccountNumber +
                ", status='" + status + '\'' +
                '}' + '\n';
    }
}
