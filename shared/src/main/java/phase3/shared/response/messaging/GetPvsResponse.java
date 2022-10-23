package phase3.shared.response.messaging;

import phase3.shared.model.messaging.PvChatOverview;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;
import java.util.LinkedList;

public class GetPvsResponse extends Response {
    public LinkedList<PvChatOverview> pvChats;

    public GetPvsResponse(LinkedList<PvChatOverview> pvChats) {
        this.pvChats = pvChats;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
