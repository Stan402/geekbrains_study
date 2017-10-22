package network;

import java.net.ServerSocket;
import java.net.Socket;

public interface ServerSocketThreadListener {

    void onStartServerSocketThread(ServerSocketThread serverSocketThread);

    void onReadyServerSocketThread(ServerSocketThread serverSocketThread, ServerSocket serverSocket);
    void onTimeout(ServerSocketThread serverSocketThread, ServerSocket serverSocket);
    void onSocketAccepted(ServerSocketThread serverSocketThread, ServerSocket serverSocket, Socket socket);

    void onStopServerSocketThread(ServerSocketThread serverSocketThread);

    void onExceptionServerSocketThread(ServerSocketThread serverSocketThread, Exception e);
}
