package dto.user.client;

import entity.Client;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetClientDto {
	private int id;
	private String email;
	
	public static GetClientDto toDto(Client client) {
		return GetClientDto
				.builder()
				.id(client.getId())
				.email(client.getEmail())
				.build();
	}
}
