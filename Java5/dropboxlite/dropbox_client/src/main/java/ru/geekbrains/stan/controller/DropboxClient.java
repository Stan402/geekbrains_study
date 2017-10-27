package ru.geekbrains.stan.controller;

import network.SocketThread;
import network.SocketThreadListener;
import util.AbstractMessage;
import util.TestMessage;

import java.io.IOException;
import java.net.Socket;

public class DropboxClient implements SocketThreadListener{

    private ClientListener eventListener;
    private SocketThread socketThread;

    public DropboxClient(ClientListener eventListener) {
        this.eventListener = eventListener;
    }

    public void putLog(String msg){
        eventListener.onClientLog(msg);
    }

    public void connect(String ipAddr, int port){
        try {
            Socket socket = new Socket(ipAddr, port);
            socketThread = new SocketThread("SocketThread",this, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(){
        AbstractMessage message = new TestMessage();
        socketThread.sendMessage(message);
    }


    public void onStartSocketThread(SocketThread socketThread) {
        putLog("SocketThread started...");
    }

    public void onStopSocketThread(SocketThread socketThread) {
        putLog("SocketThread stopped...");
    }

    public void onReadySocketThread(SocketThread socketThread, Socket socket) {
        putLog("Connection established...");

    }

    public void onRecievedMessage(SocketThread socketThread, Socket socket, AbstractMessage message) {

        putLog(message.toString());
    }

    public void onExceptionSocketThread(SocketThread socketThread, Socket socket, Exception e) {

        putLog(socketThread.getName() + " " + e.getMessage());
    }
}
