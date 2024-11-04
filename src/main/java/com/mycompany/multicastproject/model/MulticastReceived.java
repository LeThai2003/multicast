package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.Contants.contants;
import com.mycompany.multicastproject.entity.StatusUser;
import com.mycompany.multicastproject.entity.User;
import com.mycompany.multicastproject.form.Login;
import com.mycompany.multicastproject.form.Multicast;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MulticastReceived extends Thread {
    private final MulticastSocket socket;
    private final InetSocketAddress group;
    private final Set<User> users = new HashSet<>();

    public MulticastReceived(MulticastSocket socket) throws UnknownHostException {
       this.socket = socket;
       this.group = new InetSocketAddress(InetAddress.getByName(contants.MULTICAST_ADDRESS), contants.PORT);
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
                User userSender = (User) ois.readObject();
                System.out.println("Multicast received : " + userSender.toString());
                users.add(userSender);
                if( userSender.getStatusUser() == StatusUser.INPUT && !userSender.getUserId().equals(Login.userCurrent.getUserId())){
                    Multicast.addUserModel(userSender.getUsername());

                    // Serialize User object to a byte array
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
                    objectStream.writeObject(Login.userCurrent);
                    objectStream.flush();
                    byte[] userData = byteStream.toByteArray();

                    // Create DatagramPacket with serialized User data
                    DatagramPacket packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
                    socket.send(packet);
                }
                if( userSender.getStatusUser() == StatusUser.INPUT && userSender.getUserId().equals(Login.userCurrent.getUserId())){
                    Multicast.reset(users.stream().filter(user -> user.getUserId()!= userSender.getUserId()).collect(Collectors.toSet()));
                }

            } catch (Exception e) {
                interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
