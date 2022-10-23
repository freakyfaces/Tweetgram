package phase3.shared.events.messaging;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class GetPvsEvent extends Event {
    public String authToken;

    public GetPvsEvent(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getPvs(this);
    }
}
