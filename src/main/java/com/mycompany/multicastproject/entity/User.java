package com.mycompany.multicastproject.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String IP;

    public User(String username, String IP) {
        this.IP = IP;
        this.username = username;
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
}
