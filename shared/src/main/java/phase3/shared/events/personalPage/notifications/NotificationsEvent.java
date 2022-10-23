package phase3.shared.events.personalPage.notifications;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class NotificationsEvent extends Event {

    public String authToken;

    public NotificationsEvent(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.notifications(this);
    }
}
