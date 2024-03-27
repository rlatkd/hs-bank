package dto.user.admin;

import dto.user.RegisterUserDto;
import entity.Admin;
import enumeration.ActivationStatus;
import enumeration.admin.AdminType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RegisterAdminDto extends RegisterUserDto {
    public Admin toEntity(int id){
        return Admin
                .builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .status(ActivationStatus.ACTIVATE)
                .type(AdminType.SUB)
                .build();
    }
}
