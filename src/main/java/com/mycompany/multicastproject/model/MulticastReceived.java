package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.Contants.contants;
import com.mycompany.multicastproject.entity.StatusUser;
import com.mycompany.multicastproject.entity.User;
import com.mycompany.multicastproject.form.Login;

import java.io.*;
import java.net.*;

public class MulticastReceived extends Thread {
    private final MulticastSocket socket;
    private final InetSocketAddress group;

    public MulticastReceived(MulticastSocket socket) throws UnknownHostException {
       this.socket = socket;
       this.group = new InetSocketAddress(InetAddress.getByName(contants.MULTICAST_ADDRESS), contants.PORT);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[contants.MAX_MESSAGE_LENGTH];
        DatagramPacket incomingPacket = new DatagramPacket(buffer, buffer.length);
//        NetworkInterface netIf = null;
//        try {
//            netIf = NetworkInterface.getByName(contants.NETWORK_INTERFACE);
//        } catch (SocketException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            this.socket.joinGroup(group, netIf);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        while( true ){
            try{
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bis);
                User userSender = (User) ois.readObject();
                System.out.println("Multicast received : " + userSender.toString());
                if( userSender.getStatusUser() == StatusUser.INPUT && !userSender.getUserId().equals(Login.userCurrent.getUserId())){


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

            } catch (Exception e) {
                interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
