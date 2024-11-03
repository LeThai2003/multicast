package com.mycompany.multicastproject.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String IP;
    private StatusUser statusUser;

    public User() {}
    public User(String username, String IP, StatusUser statusUser) {
        this.IP = IP;
        this.username = username;
        this.statusUser = statusUser;
    }

    public String getIP() {
        return IP;
    }

    public String getUsername() {
        return username;
    }
    public void setIP(String IP) {
        this.IP = IP;
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

    @Override
    public String toString() {
        return "User{" +
                "IP='" + IP + '\'' +
                ", username='" + username + '\'' +
                ", statusUser=" + statusUser +
                '}';
    }
}
