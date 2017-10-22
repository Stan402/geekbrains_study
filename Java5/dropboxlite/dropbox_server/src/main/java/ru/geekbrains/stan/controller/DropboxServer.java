package ru.geekbrains.stan.controller;

import network.ServerSocketThread;
import network.ServerSocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;

public class DropboxServer implements ServerSocketThreadListener{

    private ServerListener eventListener;

    private ServerSocketThread serverSocketThread;

    public DropboxServer(ServerListener eventListener) {
        this.eventListener = eventListener;
    }

    public void startListening(int port){
        putLog("start listening...");
        if (serverSocketThread != null && serverSocketThread.isAlive()){
            putLog("Server is already started!");
            return;
        }
        serverSocketThread = new ServerSocketThread("ServerSocketThread", port, 2000, this);
    }

    public void dropAllClients(){
        putLog("dropAllUsers");
    }

    public void stopListening(){
        if (serverSocketThread == null || !serverSocketThread.isAlive()){
            putLog("Server is not started!");
            return;
        }
        serverSocketThread.interrupt();
    }

    private synchronized void putLog(String msg){

        eventListener.onServerLog(msg);
    }

    public void onStartServerSocketThread(ServerSocketThread serverSocketThread) {
        putLog("started...");
    }

    public void onReadyServerSocketThread(ServerSocketThread serverSocketThread, ServerSocket serverSocket) {
        putLog("ServerSocket is ready...");
    }

    public void onTimeout(ServerSocketThread serverSocketThread, ServerSocket serverSocket) {
        putLog("accept timeout");
    }

    public void onSocketAccepted(ServerSocketThread serverSocketThread, ServerSocket serverSocket, Socket socket) {
        putLog("User connected");
    }

    public void onStopServerSocketThread(ServerSocketThread serverSocketThread) {
        putLog("stopped");
    }

    public void onExceptionServerSocketThread(ServerSocketThread serverSocketThread, Exception e) {

    }
}
