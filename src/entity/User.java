package entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public abstract class User extends Entity {
    protected String name;
    protected String email;
    protected String password;
    protected String status;
    
    public final void setStatus(String status) {
    	this.status = status;
    }

	public final void setName(String name) {
		this.name = name;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final void setPassword(String password) {
		this.password = password;
	}
}
