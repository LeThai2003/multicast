package com.mycompany.multicastproject.entity;

import com.mycompany.multicastproject.form.EStatusJoin;

import java.io.Serializable;

public class JoinGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    private Group group;
    private EStatusJoin eStatusJoin;

    public JoinGroup(EStatusJoin eStatusJoin, Group group, User user) {
        this.eStatusJoin = eStatusJoin;
        this.group = group;
        this.user = user;
    }

    public EStatusJoin geteStatusJoin() {
        return eStatusJoin;
    }

    public void seteStatusJoin(EStatusJoin eStatusJoin) {
        this.eStatusJoin = eStatusJoin;
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

    @Override
    public String toString() {
        return "JoinGroup{" +
                "group=" + group +
                ", user=" + user +
                '}';
    }
}
