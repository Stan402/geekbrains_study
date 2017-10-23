package network;

import util.AbstractMessage;

import java.net.Socket;

public interface SocketThreadListener {

    void onStartSocketThread(SocketThread socketThread);
    void onStopSocketThread(SocketThread socketThread);

    void onReadySocketThread(SocketThread socketThread, Socket socket);
    void onRecievedMessage(SocketThread socketThread, Socket socket, AbstractMessage message);

    void onExceptionSocketThread(SocketThread socketThread, Socket socket, Exception e);
}
