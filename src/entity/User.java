package entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
@Getter
@SuperBuilder
public class User {
    protected String id;
    protected String password;
    protected String name;
    protected String birthDate;
    protected String gender;
    protected String email;
    protected String phoneNumber;
    protected String address;
    protected String createdAt;
    protected String status;
}
