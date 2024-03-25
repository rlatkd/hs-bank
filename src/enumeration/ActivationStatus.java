package enumeration;

import lombok.Getter;

@Getter
public enum ActivationStatus {

    ACTIVATE("활성화"),
    DEACTIVATE("비활성화");

    private String korean;
    ActivationStatus(String korean){
        this.korean = korean;
    }
}
