package com.mycompany.multicastproject.astract;

import com.mycompany.multicastproject.entity.Group;
import java.net.InetAddress;

public interface IClient {
    void login( String name);
    void joinGroup(InetAddress ipGroup, int port, String groupName);
    void sendCreateGroup( Group group);
}

