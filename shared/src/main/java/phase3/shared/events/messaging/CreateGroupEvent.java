package phase3.shared.events.messaging;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class CreateGroupEvent extends Event {
    public String authToken;
    public String username;
    public String groupName;

    public CreateGroupEvent(String authToken, String username, String groupName) {
        this.authToken = authToken;
        this.username = username;
        this.groupName = groupName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.createGroup(this);
    }
}
