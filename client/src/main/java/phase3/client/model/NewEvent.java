package phase3.client.model;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.ResponseVisitor;

public class NewEvent {
    public Event event;
    public ResponseVisitor visitor;

    public NewEvent(Event event, ResponseVisitor responseVisitor) {
        this.event = event;
        this.visitor = responseVisitor;
    }
}
