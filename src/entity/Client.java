package entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Client extends User{
    @Override
    public String toString() {
        return "";
    }
}
