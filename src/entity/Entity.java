package entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public abstract class Entity implements Serializable {
    protected int id;
    public final void setId(int id){
        this.id = id;
    }
}
