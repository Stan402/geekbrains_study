package network;

import util.AbstractMessage;
import util.TestMessage;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread{

private final SocketThreadListener eventListener;
private final Socket socket;

private ObjectOutputStream out;

    public SocketThread(String name, SocketThreadListener eventListener, Socket socket) {
        super(name);
        this.eventListener = eventListener;
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        eventListener.onStartSocketThread(this);
        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            eventListener.onReadySocketThread(this, socket);
            while (!isInterrupted()){
                AbstractMessage message;
                //read message
                message = (AbstractMessage) in.readObject();

                eventListener.onRecievedMessage(this, socket, message);
            }

        }catch (IOException | ClassNotFoundException e){
            eventListener.onExceptionSocketThread(this, socket, e);
        }
    }

    public synchronized void sendMessage(AbstractMessage message){
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            eventListener.onExceptionSocketThread(this,socket, e);
            close();
        }
    }

    public void close(){
        interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onExceptionSocketThread(this,socket, e);
        }
    }
}
