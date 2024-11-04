package com.mycompany.multicastproject.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String username;
    private StatusUser statusUser;

    public User() {}
    public User(Integer userId, String username, StatusUser statusUser) {
        this.username = username;
        this.statusUser = statusUser;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public StatusUser getStatusUser() {
        return this.statusUser;
    }
    public void setStatusUser(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", statusUser=" + statusUser +
                '}';
    }
}
