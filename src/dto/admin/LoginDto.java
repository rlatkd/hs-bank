package dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDto {
	private String email;
	private String password;
}