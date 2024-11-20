package com.mycompany.multicastproject.entity;

import java.io.Serializable;
import java.util.Set;

public class SetGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    private Set<Group> setGroup;

    public SetGroup(Set<Group> setGroup) {
        this.setGroup = setGroup;
    }

    public Set<Group> getSetGroup() {
        return setGroup;
    }

    public void setSetGroup(Set<Group> setGroup) {
        this.setGroup = setGroup;
    }

    @Override
    public String toString() {
        return "SetGroup{" +
                "setGroup=" + setGroup +
                '}';
    }
}
