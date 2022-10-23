package phase3.shared.events.personalPage;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class ListsEvent extends Event {

    public String authToken;

    public ListsEvent(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getLists(this);
    }
}
