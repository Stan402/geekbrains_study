package ru.geekbrains.stan.controller;

import network.SocketThread;
import network.SocketThreadListener;
import ru.geekbrains.stan.entity.User;

import java.net.Socket;

public class SecuritySocketThread  extends SocketThread{
    
    private boolean isAuthorized;
    private User user;

    public SecuritySocketThread(String name, SocketThreadListener eventListener, Socket socket) {
        super(name, eventListener, socket);
    }
    
    void authorizeAccept(User user){
        isAuthorized = true;
        this.user = user;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public User getUser() {
        return user;
    }
}
