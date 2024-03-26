package dto.account;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RemoveAccountDto {
    private int id;
    private int ownerId;
}
