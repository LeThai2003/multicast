package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.Contants.contants;
import com.mycompany.multicastproject.entity.Group;
import com.mycompany.multicastproject.entity.StatusUser;
import com.mycompany.multicastproject.entity.User;
import com.mycompany.multicastproject.form.Login;
import com.mycompany.multicastproject.form.Multicast;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MulticastReceived extends Thread {
    private final MulticastSocket socket;
    private final InetSocketAddress group;
    public static Set<User> users = new HashSet<>();
    private final Set<Group> groups = new HashSet<>();

    public MulticastReceived(MulticastSocket socket) throws UnknownHostException {
       this.socket = socket;
       this.group = new InetSocketAddress(InetAddress.getByName(contants.MULTICAST_ADDRESS), contants.PORT);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[contants.MAX_MESSAGE_LENGTH];
        DatagramPacket incomingPacket = new DatagramPacket(buffer, buffer.length);
        while (true) {
            try {
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bis);

                if (ois.readObject() instanceof User userSender) {
                    System.out.println("multicast received : " + userSender.toString());
                    if (userSender.getStatusUser() != StatusUser.INPUT) {
                        users.add(userSender);
                    }
                    if (userSender.getStatusUser() == StatusUser.INPUT && !userSender.getUserId().equals(Login.userCurrent.getUserId())) {
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
                    Multicast.reset(users.stream().filter(user -> !Objects.equals(user.getUserId(), Login.userCurrent.getUserId())).collect(Collectors.toSet()));
                } else {
                    System.out.println(ois.readObject());
                }
            } catch (Exception e) {
                interrupt();
                e.printStackTrace();
            }
        }
    }

}
