package phase3.client.controller.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import phase3.client.controller.EventSender;
import phase3.shared.events.Event;
import phase3.shared.gson.Deserializer;
import phase3.shared.gson.Serializer;
import phase3.shared.response.Response;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketEventSender implements EventSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketEventSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Event.class, new Serializer<>())
                .create();
    }


    @Override
    public Response send(Event event) {
        String eventString = gson.toJson(event, Event.class);
        printStream.println(eventString);
        String responseString = scanner.nextLine();
        return gson.fromJson(responseString, Response.class);
    }

    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
