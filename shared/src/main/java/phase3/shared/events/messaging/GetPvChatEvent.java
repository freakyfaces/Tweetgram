package phase3.shared.events.messaging;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class GetPvChatEvent extends Event {
    public String authToken;
    public String pvId;

    public GetPvChatEvent(String authToken, String pvId) {
        this.authToken = authToken;
        this.pvId = pvId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getPvChat(this);
    }
}
