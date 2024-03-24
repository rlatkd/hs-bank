package dto.user.client;

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
	private String gender;
	private String phoneNumber;
	private String address;
}
