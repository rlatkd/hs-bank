package dto.user.client;

import dto.user.RegisterUserDto;
import entity.Client;
import enumeration.ActivationStatus;
import enumeration.client.Gender;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import utils.DateUtils;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class RegisterClientDto extends RegisterUserDto {
	private String birthDate;
	private Gender gender;
	private String phoneNumber;

	public Client toEntity(){
		return Client
				.builder()
				.name(name)
				.email(email)
				.password(password)
				.birthDate(birthDate)
				.gender(gender)
				.phoneNumber(phoneNumber)
				.createdAt(DateUtils.dateTimeNow)
				.status(ActivationStatus.ACTIVATE)
				.build();
	}
}
