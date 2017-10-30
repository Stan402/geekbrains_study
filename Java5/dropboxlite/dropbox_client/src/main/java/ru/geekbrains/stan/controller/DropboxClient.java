package ru.geekbrains.stan.controller;

import network.SocketThread;
import network.SocketThreadListener;
import util.*;

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

    private void sendMsg(AbstractMessage message){
        socketThread.sendMessage(message);
    }

    public void disconnect(){
        socketThread.close();
    }


    public void onStartSocketThread(SocketThread socketThread) {
        putLog("SocketThread started...");
    }

    public void onStopSocketThread(SocketThread socketThread) {
        putLog("SocketThread stopped...");
    }

    public void onReadySocketThread(SocketThread socketThread, Socket socket) {
        putLog("Connection established...");
        AuthRequestMessage message = new AuthRequestMessage("John", "JohnPass");
        sendMsg(message);
    }

    public void onRecievedMessage(SocketThread socketThread, Socket socket, AbstractMessage message) {

        switch (message.getType()){
            case AUTH_ACCEPT:
                putLog(String.format("User %s accepted!", ((AuthAcceptMessage)message).getLogin()));
                break;
            case AUTH_ERROR:
                putLog("Invalid login or password. Please try again...");
                break;
            case TEST_MESSAGE:

                putLog(((TestMessage)message).getTestString());
                break;
            case TEXT_MESSAGE:
                putLog(((TextMessage)message).getMsg());
                break;
            default:
                putLog("Unknown message recieved!");
        }

        putLog(message.toString());
    }

    public void onExceptionSocketThread(SocketThread socketThread, Socket socket, Exception e) {

        putLog(socketThread.getName() + " " + e.getMessage());
    }
}
