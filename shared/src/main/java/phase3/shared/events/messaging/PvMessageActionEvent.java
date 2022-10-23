package phase3.shared.events.messaging;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class PvMessageActionEvent extends Event {

    public String authToken;
    public String pvId;
    public String action;
    public String message;
    public String messageId;
    public byte[] pic;

    public PvMessageActionEvent(String authToken, String pvId, String action, String message, String messageId, byte[] pic) {
        this.authToken = authToken;
        this.pvId = pvId;
        this.action = action;
        this.message = message;
        this.messageId = messageId;
        this.pic = pic;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.pvMessageAction(this);
    }
}
