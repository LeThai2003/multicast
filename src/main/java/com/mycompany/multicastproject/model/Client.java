package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.Contants.contants;
import com.mycompany.multicastproject.MulticastProject;
import com.mycompany.multicastproject.astract.IClient;
import com.mycompany.multicastproject.entity.User;
import com.mycompany.multicastproject.form.Multicast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class Client implements IClient {

    private final MulticastSocket socket;
    private InetSocketAddress group;
    MulticastReceived multicastReceived;

    public Client() throws IOException {
        this.socket = new MulticastSocket(contants.PORT);
        multicastReceived = new MulticastReceived(socket);
        multicastReceived.start();
    }

    @Override
    public void login(String name) {
        String username = name;
        try{
            group = new InetSocketAddress(InetAddress.getByName(contants.MULTICAST_ADDRESS), contants.PORT);
            NetworkInterface netIf = NetworkInterface.getByName(contants.NETWORK_INTERFACE);
            this.socket.joinGroup(group, netIf);
            User user = new User(name, socket.getLocalAddress().getHostAddress());


            // Serialize User object to a byte array
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(user);
            objectStream.flush();
            byte[] userData = byteStream.toByteArray();

            // Create DatagramPacket with serialized User data
            DatagramPacket packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
            MulticastProject.name = name;
            Multicast mul = new Multicast();
            mul.setName(MulticastProject.name);
            mul.setLocationRelativeTo(null);
            mul.setVisible(true);
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }
}
