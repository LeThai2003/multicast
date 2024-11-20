package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.Contants.contants;
import com.mycompany.multicastproject.entity.Group;
import com.mycompany.multicastproject.entity.SetGroup;
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
    public static Set<Group> groups = new HashSet<>();
    public static Set<Group> groupAll = new HashSet<>();

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
                Object receivedObject = ois.readObject();
                if (receivedObject instanceof User userSender) {
                    System.out.println("multicast received : " + userSender.toString());
                    if (userSender.getStatusUser() != StatusUser.INPUT) {
                        users.add(userSender);
                    }
                    if (userSender.getStatusUser() == StatusUser.INPUT && !userSender.getUserId().equals(Login.userCurrent.getUserId())) {
                        Multicast.addUserModel(userSender.getUsername());

                        try {
                            // Serialize User object to a byte array
                            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

                            // Serialize Login.userCurrent
                            objectStream.writeObject(Login.userCurrent);
                            objectStream.flush();
                            byte[] userData = byteStream.toByteArray();

                            // Create and send DatagramPacket with serialized User data
                            DatagramPacket packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
                            socket.send(packet);

                            // Serialize MulticastReceived.groupAll
                            byteStream.reset(); // Clear the ByteArrayOutputStream for reuse
                            objectStream.writeObject(MulticastReceived.groupAll);
                            objectStream.flush();
                            userData = byteStream.toByteArray();

                            // Create and send DatagramPacket with serialized groupAll data
                            packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
                            socket.send(packet);

                            // Close streams
                            objectStream.close();
                            byteStream.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Multicast.reset(users.stream().filter(user -> !Objects.equals(user.getUsername(), Login.userCurrent.getUsername())).collect(Collectors.toSet()));
                }
                else if( receivedObject instanceof Group groupSender )
                {
                    System.out.println("Recei group new");
                    groupAll.add(groupSender);
                    if( groupSender.getUsers().contains(Login.userCurrent)){
                        groups.add(groupSender);
                        Multicast.resetGroup(groups);
                    }
                }else if( receivedObject instanceof SetGroup setGroup ){
                    System.out.println(setGroup.getSetGroup());
                }
            } catch (Exception e) {
                interrupt();
                e.printStackTrace();
            }
        }
    }

}
