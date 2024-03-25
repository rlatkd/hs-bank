package dto.user.client;

import enumeration.client.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateClientDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String birthDate;
	private Gender gender;
	private String phoneNumber;
}
