package dto.user;

import entity.User;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class RegisterUserDto {
	protected String name;
	protected String email;
	protected String password;

	public abstract User toEntity();
}
