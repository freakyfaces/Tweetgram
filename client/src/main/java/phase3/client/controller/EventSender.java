package phase3.client.controller;

import phase3.shared.events.Event;
import phase3.shared.response.Response;

public interface EventSender {

    Response send(Event event);
    void close();


}

