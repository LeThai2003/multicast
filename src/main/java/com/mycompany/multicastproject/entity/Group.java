package com.mycompany.multicastproject.entity;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    private int port;
    private InetAddress IP;
    private String nameGroup;
    private List<User> users = new ArrayList<>();
    private Set<User> usersJoined = new HashSet<>();

    public InetAddress getIP() {
        return IP;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setIP(InetAddress IP) {
        this.IP = IP;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Set<User> getUsersJoined() {
        return usersJoined;
    }

    public void setUsersJoined(Set<User> usersJoined) {
        this.usersJoined = usersJoined;
    }

    @Override
    public String toString() {
        return getNameGroup();
    }
}
