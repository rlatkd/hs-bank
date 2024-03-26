package dto.transaction;

import entity.Transaction;
import enumeration.transaction.TransactionStatus;
import enumeration.transaction.TransactionType;
import lombok.Builder;
import lombok.Getter;
import utils.DateTimeGenerator;

@Builder
@Getter
public class TransferDto {
    private int withdrawAccountId;
    private int withdrawAccountOwnerId;
    private String depositAccountNumber;
    private long amount;

    public Transaction toEntity(int depositAccountId){
        return Transaction.builder().
                date(DateTimeGenerator.getDateTimeNow()).
                type(TransactionType.TRANSFER).
                amount(amount).
                withdrawAccountId(withdrawAccountId).
                depositAccountId(depositAccountId).
                status(TransactionStatus.COMPLETE).
                build();
    }
}
