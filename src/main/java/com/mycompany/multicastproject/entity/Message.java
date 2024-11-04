package com.mycompany.multicastproject.entity;

import java.io.Serializable;
import java.time.LocalTime;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String content;
    private LocalTime time;
    private User user;

    public Message(String content, LocalTime time, User user) {
        this.content = content;
        this.time = time;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "[" + time + "]" + user.getUsername() + "> " + content;
    }
}
