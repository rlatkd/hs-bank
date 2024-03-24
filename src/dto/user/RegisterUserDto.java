package dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUserDto {
	private String email;
	private String password;
	private String name;
	private String birthDate;
	private String gender;
	private String phoneNumber;
	private String address;
}
