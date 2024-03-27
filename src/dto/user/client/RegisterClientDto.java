package dto.user.client;

import dto.user.RegisterUserDto;
import entity.Client;
import enumeration.ActivationStatus;
import enumeration.client.Gender;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import utils.DateTimeGenerator;

@Getter
@SuperBuilder
public class RegisterClientDto extends RegisterUserDto {
	private String birthDate;
	private Gender gender;
	private String phoneNumber;

	public Client toEntity(int id){
		return Client
				.builder()
				.id(id)
				.name(name)
				.email(email)
				.password(password)
				.birthDate(birthDate)
				.gender(gender)
				.phoneNumber(phoneNumber)
				.point(0)
				.createdAt(DateTimeGenerator.getDateTimeNow())
				.status(ActivationStatus.ACTIVATE)
				.build();
	}
}
