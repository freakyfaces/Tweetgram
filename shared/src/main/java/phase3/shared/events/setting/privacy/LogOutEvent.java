package phase3.shared.events.setting.privacy;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class LogOutEvent extends Event {

    String authToken;

    public LogOutEvent(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.logOut(this);
    }
}
