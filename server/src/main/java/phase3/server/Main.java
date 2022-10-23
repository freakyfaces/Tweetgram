package phase3.server;

import phase3.server.controller.SocketManager;
import phase3.server.dataBase.Load;
import phase3.server.dataBase.Save;
import phase3.shared.util.Loop;

import java.util.LinkedList;


public class Main {

    public static LinkedList<String> onlineUsers = new LinkedList<>();

    public static void main(String[] args) {

        Load.readData();
        SocketManager socketManager = new SocketManager();
        socketManager.start();
        Loop saveLoop = new Loop(1, Save::saveData);
        saveLoop.start();

    }
}
