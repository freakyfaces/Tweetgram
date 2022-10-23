package phase3.shared.events.messaging;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class GetGroupEvent extends Event {
    public String authToken;
    public String groupId;

    public GetGroupEvent(String authToken, String groupId) {
        this.authToken = authToken;
        this.groupId = groupId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getGroup(this);
    }
}
