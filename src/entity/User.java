package entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public abstract class User implements Serializable {
    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected String status;
    
    public void setId(int id) {
    	this.id = id;
    }
    
    
    
    public void setStatus(String status) {
    	this.status = status;
    }



	public void setName(String name) {
		this.name = name;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPassword(String password) {
		this.password = password;
	}
}
