package entity;

import enumeration.admin.AdminType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public class Admin extends User {
    private AdminType type;

    public void setType(AdminType type) {
        this.type = type;
    }
}
