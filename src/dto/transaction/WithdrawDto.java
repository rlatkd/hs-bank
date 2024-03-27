package dto.transaction;

import entity.Transaction;
import enumeration.transaction.TransactionStatus;
import enumeration.transaction.TransactionType;
import lombok.Builder;
import lombok.Getter;
import utils.DateTimeGenerator;

@Builder
@Getter
public class WithdrawDto {
    private int accountId;
    private int ownerId;
    private long amount;

    public Transaction toEntity(int id){
        return Transaction.builder()
                .id(id)
                .date(DateTimeGenerator.getDateTimeNow())
                .type(TransactionType.WITHDRAW)
                .amount(amount)
                .withdrawAccountId(accountId)
                .status(TransactionStatus.COMPLETE)
                .build();
    }
}
