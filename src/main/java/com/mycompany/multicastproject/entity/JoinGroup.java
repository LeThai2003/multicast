package com.mycompany.multicastproject.entity;

import java.io.Serializable;

public class JoinGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    private Group group;

    public JoinGroup(Group group, User user) {
        this.group = group;
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
