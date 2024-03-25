package enumeration;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum ActivationStatus implements Serializable {

    ACTIVATE("활성화"),
    DEACTIVATE("비활성화");

    private String korean;
    ActivationStatus(String korean){
        this.korean = korean;
    }
}
