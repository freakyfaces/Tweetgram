package phase3.shared.response.messaging;

import phase3.shared.model.messaging.pvChat;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

import java.util.LinkedList;

public class GetPvChatResponse extends Response {
    public pvChat pvChat;
    public LinkedList<byte[]> pics;

    public GetPvChatResponse(phase3.shared.model.messaging.pvChat pvChat, LinkedList<byte[]> pics) {
        this.pvChat = pvChat;
        this.pics = pics;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
