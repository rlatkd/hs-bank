package dto.user.client;

import dto.user.RegisterUserDto;
import entity.Client;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import utils.DateUtils;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class RegisterClientDto extends RegisterUserDto {
	private String birthDate;
	private String gender;
	private String phoneNumber;
	private String address;

	public Client toEntity(){
		return Client
				.builder()
				.name(name)
				.email(email)
				.password(password)
				.birthDate(birthDate)
				.gender(gender)
				.phoneNumber(phoneNumber)
				.address(address)
				.createdAt(DateUtils.dateTimeNow)
				.status("active")
				.build();
	}
}
