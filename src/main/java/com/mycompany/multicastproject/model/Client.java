package com.mycompany.multicastproject.model;

import com.mycompany.multicastproject.Contants.contants;
import com.mycompany.multicastproject.MulticastProject;
import com.mycompany.multicastproject.astract.IClient;
import com.mycompany.multicastproject.entity.Group;
import com.mycompany.multicastproject.entity.Message;
import com.mycompany.multicastproject.entity.StatusUser;
import com.mycompany.multicastproject.entity.User;
import com.mycompany.multicastproject.form.Login;
import com.mycompany.multicastproject.form.Multicast;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements IClient {
    private final MulticastSocket socket;
    private MulticastSocket sender;
    private InetSocketAddress group;
    MulticastReceived multicastReceived;
    MessageReceived messageReceived;

    public Client() throws IOException {
        this.socket = new MulticastSocket(contants.PORT);
        multicastReceived = new MulticastReceived(socket);
        multicastReceived.start();
    }
//    public Client(int port) throws IOException {
//        this.socket = new MulticastSocket(port);
//        multicastReceived = new MulticastReceived(socket);
//        multicastReceived.start();
//    }
    @Override
    public void login(String name) {
        String username = name;
        try{
            group = new InetSocketAddress(InetAddress.getByName(contants.MULTICAST_ADDRESS), contants.PORT);
            NetworkInterface netIf = NetworkInterface.getByName(contants.NETWORK_INTERFACE);
            this.socket.joinGroup(group, netIf);


            // Serialize User object to a byte array
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(Login.userCurrent);
            objectStream.flush();
            byte[] userData = byteStream.toByteArray();

            // Create DatagramPacket with serialized User data
            DatagramPacket packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
            socket.send(packet);
            Login.userCurrent.setStatusUser(StatusUser.ACTIVE);
            MulticastProject.name = name;
            Multicast mul = new Multicast();
            mul.setName(MulticastProject.name);
            mul.setEnableButtonJoin(false);
            mul.setEnableButtonLeave(false);
            mul.setEnableButtonSend(false);
            mul.setLocationRelativeTo(null);
            mul.setVisible(true);
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public void joinGroup(InetAddress ipGroup, int port, String groupName) {
        try {
            this.sender = new MulticastSocket(port);
            NetworkInterface netIf = NetworkInterface.getByName(contants.NETWORK_INTERFACE);
            group = new InetSocketAddress(ipGroup, port);
            this.sender.joinGroup(group, netIf);
            messageReceived = new MessageReceived(sender);
            messageReceived.start();
            sendMessage(Login.userCurrent.getUsername() + " into group");
//            sender.leaveGroup(ipGroup);
            // Gửi tin nhắn từ người dùng đến nhóm
//            System.out.println("Nhập tin nhắn để gửi, gõ 'exit' để thoát.");
//            String message;
//            while (!(message = scanner.nextLine()).equalsIgnoreCase("exit")) {
//                sendMessage(newSocket, ipGroup, port, message);
//            }
//
//            newSocket.leaveGroup(ipGroup);
//            newSocket.close();
//            System.out.println("Đã rời khỏi nhóm " + groupName);
            
        }catch ( Exception e ){
            // xoa nhom khi out het
            e.printStackTrace();
        }
    }

    @Override
    public void sendCreateGroup(Group groupCreate) {
        try {
            // Initialize the ByteArrayOutputStream and ObjectOutputStream correctly
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteStream); // Corrected this line

            // Write the Group object to the ObjectOutputStream
            oos.writeObject(groupCreate);
            oos.flush();

            // Get the byte array from the ByteArrayOutputStream
            byte[] userData = byteStream.toByteArray();

            // Create DatagramPacket with serialized User data
            DatagramPacket packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
            socket.send(packet);

            // Close the streams after use
            oos.close(); // Close ObjectOutputStream
            byteStream.close(); // Close ByteArrayOutputStream
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) {
        try{
            Message mess = new Message(message, LocalTime.now(),Login.userCurrent);
            // Initialize the ByteArrayOutputStream and ObjectOutputStream correctly
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteStream); // Corrected this line

            // Write the Group object to the ObjectOutputStream
            oos.writeObject(mess);
            oos.flush();

            // Get the byte array from the ByteArrayOutputStream
            byte[] userData = byteStream.toByteArray();

            // Create DatagramPacket with serialized User data
            DatagramPacket packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
            sender.send(packet);
            System.out.println("Client 141 : send group");
            // Close the streams after use
            oos.close(); // Close ObjectOutputStream
            byteStream.close(); // Close ByteArrayOutputStream
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private static void sendMessage(MulticastSocket socket,InetAddress ipGroup, int port, String message) {
        try {
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipGroup, port);
            socket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
