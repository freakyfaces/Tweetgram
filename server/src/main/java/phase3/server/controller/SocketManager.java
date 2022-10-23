package phase3.server.controller;

import phase3.server.controller.network.SocketResponseSender;
import phase3.shared.config.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager extends Thread{

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Server.serverAddress);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(new SocketResponseSender(socket));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
