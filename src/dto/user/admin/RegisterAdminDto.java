package dto.user.admin;

import dto.user.RegisterUserDto;
import entity.Admin;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RegisterAdminDto extends RegisterUserDto {
    public Admin toEntity(){
        return Admin
                .builder()
                .name(name)
                .email(email)
                .password(password)
                .status("sub")
                .build();
    }
}
