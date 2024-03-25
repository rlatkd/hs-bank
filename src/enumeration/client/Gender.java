package enumeration.client;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("남자"),
    FEMALE("여자");

    private String korean;
    Gender(String korean){
        this.korean = korean;
    }
}
