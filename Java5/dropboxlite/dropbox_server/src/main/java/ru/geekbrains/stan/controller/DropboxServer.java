package ru.geekbrains.stan.controller;

import network.ServerSocketThread;
import network.ServerSocketThreadListener;
import network.SocketThread;
import network.SocketThreadListener;
import ru.geekbrains.stan.entity.User;
import ru.geekbrains.stan.service.ClientService;
import util.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class DropboxServer implements ServerSocketThreadListener, SocketThreadListener{

    private ServerListener eventListener;
    private ClientService clientService = new ClientService();

    private ServerSocketThread serverSocketThread;
    private final CopyOnWriteArrayList<SocketThread> clients = new CopyOnWriteArrayList<SocketThread>();

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
        putLog("Server socket started...");
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
        new SecuritySocketThread(threadName, this, socket);
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
        putLog("New Socket thread started...");
    }

    public void onStopSocketThread(SocketThread socketThread) {

        putLog("SocketThread stopped...");
    }

    public void onReadySocketThread(SocketThread socketThread, Socket socket) {
        putLog("Socket is ready");
        clients.add(socketThread);
    }

    public void onRecievedMessage(SocketThread socketThread, Socket socket, AbstractMessage message) {
        putLog("message resieved...");

        SecuritySocketThread client = (SecuritySocketThread) socketThread;
        if (client.isAuthorized())
            handleAuthorizedClient(client, message);
        else
            handleNonAuthorizedClient(client, message);

        putLog(((TestMessage)message).getTestString());

    }

    private void handleNonAuthorizedClient(SecuritySocketThread client, AbstractMessage message) {
        putLog("handling non authorizied client");
        if (message.getType() != MessageType.AUTH_REQUEST){
            client.sendMessage(new TextMessage("Authorize first!"));
            return;
        }
        putLog("sending request to db...");
        User user = clientService.retrieveUser(((AuthRequestMessage)message).getLogin(),
                                                ((AuthRequestMessage)message).getPassword());
        putLog("checking result...");

        if (user == null)
            putLog("Unknown User");
        else
            putLog(user.toString());
    }

    private void handleAuthorizedClient(SecuritySocketThread client, AbstractMessage message) {

    }

    public void onExceptionSocketThread(SocketThread socketThread, Socket socket, Exception e) {
        putLog("Exception: " + e.getClass().getName() + ": " + e.getMessage());
    }
}
