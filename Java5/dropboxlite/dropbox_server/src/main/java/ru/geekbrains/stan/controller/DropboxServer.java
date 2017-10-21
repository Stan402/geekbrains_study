package ru.geekbrains.stan.controller;

public class DropboxServer {

    private ServerListener eventListener;

    public DropboxServer(ServerListener eventListener) {
        this.eventListener = eventListener;
    }

    public void startListening(int port){
        putLog("started...");
    }

    public void dropAllClients(){
        putLog("dropAllUsers");
    }

    public void stopListening(){
        putLog("stopped...");
    }

    private synchronized void putLog(String msg){

        eventListener.onServerLog(msg);
    }
}
