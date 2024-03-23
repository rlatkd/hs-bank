package entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public class User implements Serializable {
    protected int id;
    protected String email;
    protected String password;
    protected String name;
    protected String birthDate;
    protected String gender;
    protected String phoneNumber;
    protected String address;
    protected String createdAt;
    protected String status;
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
}
