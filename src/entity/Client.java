package entity;

import enumeration.client.Gender;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Client extends User{
	private String birthDate;
	private Gender gender;
	private String phoneNumber;
	private String createdAt;
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
