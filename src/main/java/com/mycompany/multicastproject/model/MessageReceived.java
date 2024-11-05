package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.entity.Group;
import com.mycompany.multicastproject.entity.Message;
import com.mycompany.multicastproject.form.Multicast;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

public class MessageReceived extends Thread {
    private final MulticastSocket socket;
    private List<Message> messageList;

    public MessageReceived( MulticastSocket socket){
        this.socket = socket;
        this.messageList = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                byte[] data = receivePacket.getData();
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bis);
                Object receivedObject = ois.readObject();
                if( receivedObject instanceof Message messageSender )
                {
                    messageList.add(messageSender);
                    Multicast.addMessage(messageSender);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
