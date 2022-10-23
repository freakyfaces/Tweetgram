package phase3.server.controller;

import phase3.shared.events.Event;
import phase3.shared.response.Response;

public interface ResponseSender {
    Event getEvent();

    void sendResponse(Response response);

    void close();
}
