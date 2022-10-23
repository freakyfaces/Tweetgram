package phase3.client.controller;

import phase3.shared.events.Event;

public interface EventListener {
    void listen(Event event);
}
