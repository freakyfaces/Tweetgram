package phase3.shared.events.personalPage.notifications;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class RequestEvent extends Event {

    public String authToken;
    public String username;
    public String status;

    public RequestEvent(String authToken, String username, String status) {
        this.authToken = authToken;
        this.username = username;
        this.status = status;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.request(this);
    }

}
