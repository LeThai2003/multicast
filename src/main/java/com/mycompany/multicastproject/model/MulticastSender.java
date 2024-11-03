package com.mycompany.multicastproject.model;

import java.net.MulticastSocket;

public class MulticastSender {
    private final MulticastSocket socket;
    private final String username;
    private final String port;

    public MulticastSender(MulticastSocket socket,final String username, final String port) {
        this.socket = socket;
        this.username = username;
        this.port = port;
    }
}
