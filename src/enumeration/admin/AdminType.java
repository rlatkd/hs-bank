package enumeration.admin;

import lombok.Getter;

@Getter
public enum AdminType {

    MAIN("메인 관리자"),
    SUB("서브 관리자");

    private String korean;
    AdminType(String korean){
        this.korean = korean;
    }
}
