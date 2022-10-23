package phase3.shared.response.messaging;

import phase3.shared.model.messaging.pvChat;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class SearchPvResponse extends Response {
    public String Message;
    public pvChat pvChat;

    public SearchPvResponse(String message, pvChat pvChat) {
        this.Message = message;
        this.pvChat = pvChat;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
