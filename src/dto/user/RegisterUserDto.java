package dto.user;

import entity.Client;
import entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import utils.DateUtils;

@Getter
@SuperBuilder
public abstract class RegisterUserDto {
	protected String name;
	protected String email;
	protected String password;

	public abstract User toEntity();
}
