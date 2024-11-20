package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.entity.Group;
import com.mycompany.multicastproject.entity.JoinGroup;
import com.mycompany.multicastproject.entity.Message;
import com.mycompany.multicastproject.entity.User;
import com.mycompany.multicastproject.form.Multicast;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                else if( receivedObject instanceof JoinGroup joinGroup){
                    Optional<Group> group = MulticastReceived.groups.stream().filter(g -> g.getIP().equals(joinGroup.getGroup().getIP())).findFirst();
                    if( group.isPresent() ){
                        group.get().getUsersJoined().add(joinGroup.getUser());
                        Message message = new Message(joinGroup.getUser().getUsername() + " into group", LocalTime.now(),joinGroup.getUser());
                        messageList.add(message);
                        Multicast.addMessage(message);
                    }
                }
            }
        } catch (Exception e) {
            interrupt();
            e.printStackTrace();
        }
    }
}
