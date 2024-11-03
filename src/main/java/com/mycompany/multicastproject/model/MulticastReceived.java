package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.Contants.contants;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MulticastReceived extends Thread {
    private final MulticastSocket socket;

    public MulticastReceived(MulticastSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[contants.MAX_MESSAGE_LENGTH];
        DatagramPacket incomingPacket = new DatagramPacket(buffer, buffer.length);

        while( true ){
            try{
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bis);
                System.out.println("multicast received : " + ois.readObject().toString());
            } catch (Exception e) {
                interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
