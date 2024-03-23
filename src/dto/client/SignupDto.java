package dto.client;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupDto {
	private String email;
	private String password;
	private String name;
	private String birthDate;
	private String gender;
	private String phoneNumber;
	private String address;
}
