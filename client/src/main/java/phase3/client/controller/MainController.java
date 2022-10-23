package phase3.client.controller;

import phase3.client.model.NewEvent;
import phase3.shared.events.Event;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.util.Loop;

import java.util.LinkedList;
import java.util.List;

public class MainController {
    public static String authToken1 = "";
    public static String username1;
    private final EventSender eventSender;
    private final List<NewEvent> events;
    private final Loop loop;
    public String authToken;
    public MainController(EventSender eventSender) {
        this.eventSender = eventSender;
        this.events = new LinkedList<>();
        this.loop = new Loop(10, this::sendEvents);
    }

    public void start(){
        loop.start();
    }

    public void addEvent(NewEvent event) {
        synchronized (events) {
            events.add(event);
        }
    }

    private void sendEvents() {
        List<NewEvent> temp;
        synchronized (events) {
            temp = new LinkedList<>(events);
            events.clear();
        }
        for (NewEvent event : temp) {
            Response response = eventSender.send(event.event);
            if (response != null){
                response.visit(event.visitor);
            }
        }

    }
}
