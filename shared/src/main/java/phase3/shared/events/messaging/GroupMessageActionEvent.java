package phase3.shared.events.messaging;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class GroupMessageActionEvent extends Event {
    public String authToken;
    public String groupId;
    public String action;
    public String message;
    public String messageId;
    public byte[] pic;

    public GroupMessageActionEvent(String authToken, String groupId, String action, String message, String messageId, byte[] pic) {
        this.authToken = authToken;
        this.groupId = groupId;
        this.action = action;
        this.message = message;
        this.messageId = messageId;
        this.pic = pic;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.groupMessageActionEvent(this);
    }
}
