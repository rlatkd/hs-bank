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

	public static GetCurrentClientDto toDto(Client client) {
		return GetCurrentClientDto
				.builder()
				.name(client.getName())
				.email(client.getEmail())
				.password(client.getPassword())
				.birthDate(client.getBirthDate())
				.gender(client.getGender().getKorean())
				.phoneNumber(client.getPhoneNumber())
				.build();
	}
}