package ru.geekbrains.stan.controller;

import network.ServerSocketThread;
import network.ServerSocketThreadListener;
import network.SocketThread;
import network.SocketThreadListener;
import util.AbstractMessage;
import util.TestMessage;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class DropboxServer implements ServerSocketThreadListener, SocketThreadListener{

    private ServerListener eventListener;

    private ServerSocketThread serverSocketThread;
    private final CopyOnWriteArrayList<SocketThread> users = new CopyOnWriteArrayList<SocketThread>();

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

    //ServerSocketThread
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
        String threadName = "Socket thread: " + socket.getInetAddress() + ":" + socket.getPort();
        new SocketThread(threadName, this,socket);
    }

    public void onStopServerSocketThread(ServerSocketThread serverSocketThread) {
        putLog("stopped");
    }

    public void onExceptionServerSocketThread(ServerSocketThread serverSocketThread, Exception e) {
        putLog("ServerSocketThread Exception: " + e.getMessage());
        e.printStackTrace();
    }

    //SocketThread
    public void onStartSocketThread(SocketThread socketThread) {
        putLog("started...");
    }

    public void onStopSocketThread(SocketThread socketThread) {

        putLog("SocketThread stopped...");
    }

    public void onReadySocketThread(SocketThread socketThread, Socket socket) {
        putLog("Socket is ready");
        users.add(socketThread);
    }

    public void onRecievedMessage(SocketThread socketThread, Socket socket, AbstractMessage message) {
        putLog("message resieved...");
        putLog(((TestMessage)message).getTestString());

    }

    public void onExceptionSocketThread(SocketThread socketThread, Socket socket, Exception e) {
        putLog("Exception: " + e.getClass().getName() + ": " + e.getMessage());
    }
}
