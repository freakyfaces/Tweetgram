package phase3.shared.response.messaging;


import phase3.shared.model.messaging.GroupChatOverview;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;
import java.util.LinkedList;

public class GetGroupsResponse extends Response {
    public LinkedList<GroupChatOverview> groupChats;
    public GetGroupsResponse(LinkedList<GroupChatOverview> groupChats) {
        this.groupChats = groupChats;
    }
    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
