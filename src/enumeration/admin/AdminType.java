package enumeration.admin;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum AdminType implements Serializable {

    MAIN("메인 관리자"),
    SUB("서브 관리자");

    private String korean;
    private AdminType(String korean){
        this.korean = korean;
    }
}
