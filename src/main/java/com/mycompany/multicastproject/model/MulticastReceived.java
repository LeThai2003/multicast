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
    public static int maxLastNumber = 10;
    public static int baseSegment = 0;

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
                    if (userSender.getStatusUser() == StatusUser.INPUT && !Login.userCurrent.getUserId().equals(userSender.getUserId())) {
                        Multicast.addUserModel(userSender.getUsername());

                        try {
                            // Khởi tạo ByteArrayOutputStream và ObjectOutputStream
                            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

                            // Gửi đối tượng User (Login.userCurrent)
                            objectStream.writeObject(Login.userCurrent);
                            objectStream.flush();
                            byte[] userData = byteStream.toByteArray();
                            DatagramPacket packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
                            socket.send(packet);

                            // Đóng luồng
                            objectStream.close();
                            byteStream.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sendGroupAll(new SetGroup(MulticastReceived.groupAll));
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
                    setGroup.getSetGroup().forEach( g -> {
                        String[] ipParts = g.getIP().getHostAddress().split("\\.");
                        int currentBaseSegment = Integer.parseInt(ipParts[ipParts.length - 2]); // octet thứ 3
                        int lastNumber = Integer.parseInt(ipParts[ipParts.length - 1]);
                        // Cập nhật countGroup và baseSegment dựa trên IP lớn nhất
                        if (currentBaseSegment > baseSegment || 
                            (currentBaseSegment == baseSegment && lastNumber > maxLastNumber)) {
                            baseSegment = currentBaseSegment;
                            maxLastNumber = lastNumber;
                        }
                        MulticastReceived.groupAll.add(g);
                    } );
                }
            } catch (Exception e) {
                interrupt();
                e.printStackTrace();
            }
        }
    }

    private void sendGroupAll( SetGroup setGroup) {
        try{
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

//                            // Gửi đối tượng User (Login.userCurrent)
//                            objectStream.writeObject(Login.userCurrent);
//                            objectStream.flush();
//                            byte[] userData = byteStream.toByteArray();
//                            DatagramPacket packet = new DatagramPacket(userData, userData.length, group.getAddress(), contants.PORT);
//                            socket.send(packet);
//
//                            // Gửi đối tượng groupAll (được đóng gói trong SetGroup)
//                            byteStream.reset(); // Xóa dữ liệu trong ByteArrayOutputStream để tái sử dụng
            objectStream.writeObject(new SetGroup(MulticastReceived.groupAll));
            objectStream.flush();
            byte[] groupData = byteStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(groupData, groupData.length, group.getAddress(), contants.PORT);
            socket.send(packet);

            // Đóng luồng
            objectStream.close();
            byteStream.close();
        }catch (IOException ex ){
            ex.printStackTrace();
        }
    }

}
