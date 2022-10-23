package phase3.shared.response.messaging;

import phase3.shared.model.messaging.GroupChat;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;
import java.util.LinkedList;

public class GetGroupResponse extends Response {
    public GroupChat groupChat;
    public LinkedList<byte[]> pics;
    public GetGroupResponse(GroupChat groupChat, LinkedList<byte[]> pics) {
        this.groupChat = groupChat;
        this.pics = pics;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
