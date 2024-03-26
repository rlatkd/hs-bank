package dto.transaction;

import entity.Transaction;
import enumeration.transaction.TransactionStatus;
import enumeration.transaction.TransactionType;
import lombok.Builder;
import lombok.Getter;
import utils.DateTimeGenerator;

@Builder
@Getter
public class DepositDto {
    private int accountId;
    private int ownerId;
    private long amount;

    public Transaction toEntity(){
        return Transaction.builder().
                date(DateTimeGenerator.getDateTimeNow()).
                type(TransactionType.DEPOSIT).
                amount(amount).
                depositAccountId(accountId).
                status(TransactionStatus.COMPLETE).
                build();
    }
}
