package entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Admin extends User{
    private String accessLevel;

    @Override
    public String toString() {
        return "";
    }
}
