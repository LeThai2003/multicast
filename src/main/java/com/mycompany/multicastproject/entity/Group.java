package com.mycompany.multicastproject.entity;

import java.io.Serializable;
import java.net.InetAddress;

public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    private int port;
    private InetAddress IP;
    private String nameGroup;
//    private List<User> users = new ArrayList<>();

    public InetAddress getIP() {
        return IP;
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

    @Override
    public String toString() {
        return getNameGroup();
    }
}
