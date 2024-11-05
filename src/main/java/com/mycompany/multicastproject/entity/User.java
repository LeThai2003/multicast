package com.mycompany.multicastproject.entity;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && statusUser == user.statusUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, statusUser);
    }
}
