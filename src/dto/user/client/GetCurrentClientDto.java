package dto.user.client;

import entity.Client;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCurrentClientDto {
	private String name;
	private String email;
	private String password;
	private String birthDate;
	private String gender;
	private String phoneNumber;
	private int point;

	public static GetCurrentClientDto toDto(Client client) {
		return GetCurrentClientDto
				.builder()
				.name(client.getName())
				.email(client.getEmail())
				.password(client.getPassword())
				.birthDate(client.getBirthDate())
				.gender(client.getGender().getKorean())
				.phoneNumber(client.getPhoneNumber())
				.point(client.getPoint())
				.build();
	}

	@Override
	public String toString() {
		return "GetCurrentClientDto{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", birthDate='" + birthDate + '\'' +
				", gender='" + gender + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", point='" + point + '\'' +
				'}' + '\n';
	}
}