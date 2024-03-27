package dto.user.client;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AddPointDto {
    int clientId;
    int point;
}
