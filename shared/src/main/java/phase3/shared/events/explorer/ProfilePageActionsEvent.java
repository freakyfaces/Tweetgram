package phase3.shared.events.explorer;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class ProfilePageActionsEvent extends Event {
    public String authToken;
    public String action;
    public String username;

    public ProfilePageActionsEvent(String authToken, String action, String username) {
        this.authToken = authToken;
        this.action = action;
        this.username = username;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.profileAction(this);
    }
}
