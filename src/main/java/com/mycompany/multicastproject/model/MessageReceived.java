package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.Contants.contants;
import com.mycompany.multicastproject.entity.Group;
import com.mycompany.multicastproject.entity.JoinGroup;
import com.mycompany.multicastproject.entity.Message;
import com.mycompany.multicastproject.entity.User;
import com.mycompany.multicastproject.form.EStatusJoin;
import com.mycompany.multicastproject.form.Login;
import com.mycompany.multicastproject.form.Multicast;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class MessageReceived extends Thread {
    private final MulticastSocket socket;
    private List<Message> messageList;

    public MessageReceived( MulticastSocket socket ) {
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
                    Optional<Group> group = MulticastReceived.groupAll.stream().filter(g -> g.getIP().equals(joinGroup.getGroup().getIP())).findFirst();

                    if( joinGroup.geteStatusJoin() == EStatusJoin.JOIN && joinGroup.getUser().getUserId() != Login.userCurrent.getUserId() ){
                        JoinGroup joinGroupRes = new JoinGroup( EStatusJoin.ACTIVE, joinGroup.getGroup() , Login.userCurrent);
                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(byteStream); // Corrected this line

                        // Write the Group object to the ObjectOutputStream
                        oos.writeObject(joinGroupRes);
                        oos.flush();

                        // Get the byte array from the ByteArrayOutputStream
                        byte[] userData = byteStream.toByteArray();
                        InetSocketAddress groupInet = new InetSocketAddress(joinGroup.getGroup().getIP(), joinGroup.getGroup().getPort());
                        // Create DatagramPacket with serialized User data
                        DatagramPacket packet = new DatagramPacket(userData, userData.length, groupInet.getAddress() , contants.PORT);
                        socket.send(packet);
                        // Close the streams after use
                        oos.close(); // Close ObjectOutputStream
                        byteStream.close(); // Close ByteArrayOutputStream
                    }
                    if( joinGroup.geteStatusJoin().equals(EStatusJoin.ACTIVE) ){
                        group.get().getUsersJoined().add(joinGroup.getUser());
                        Multicast.resetAll( new HashSet<>(group.get().getUsersJoined()));
                    }
                    System.out.println(joinGroup.geteStatusJoin().equals(EStatusJoin.JOIN));
                    if( group.isPresent() && joinGroup.geteStatusJoin().equals(EStatusJoin.JOIN)){
                        System.out.println(group.get());
                        group.get().getUsersJoined().add(joinGroup.getUser());
                        Message message = new Message(joinGroup.getUser().getUsername() + " into group", LocalTime.now(),joinGroup.getUser());
                        messageList.add(message);
                        Multicast.addMessage(message);
                        System.out.println(joinGroup.toString());
                        Multicast.resetAll( new HashSet<>(group.get().getUsersJoined()));
                    }
                }
            }
        } catch (Exception e) {
            interrupt();
            e.printStackTrace();
        }
    }
}
