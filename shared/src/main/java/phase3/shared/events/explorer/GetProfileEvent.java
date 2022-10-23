package phase3.shared.events.explorer;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class GetProfileEvent extends Event {

    public String authToken;
    public String username;

    public GetProfileEvent(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getProfile(this);
    }
}
