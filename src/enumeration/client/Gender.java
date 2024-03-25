package enumeration.client;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Gender implements Serializable {

    MALE("남자"),
    FEMALE("여자");

    private String korean;
    private Gender(String korean){
        this.korean = korean;
    }
}
