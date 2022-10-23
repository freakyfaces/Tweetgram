package phase3.shared.events.authentication;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class CloseAppEvent extends Event {

    public String authToken;

    public CloseAppEvent(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.closeApp(this);
    }
}
