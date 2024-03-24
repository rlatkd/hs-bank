package entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Client extends User{
	private String birthDate;
	private String gender;
	private String phoneNumber;
	private String address;
	private String createdAt;
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
