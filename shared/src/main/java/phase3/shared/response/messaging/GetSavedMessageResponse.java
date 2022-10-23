package phase3.shared.response.messaging;

import phase3.shared.model.messaging.SavedMessage;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

import java.util.LinkedList;

public class GetSavedMessageResponse extends Response {
    public SavedMessage savedMessage;
    public LinkedList<byte[]> pics;

    public GetSavedMessageResponse(SavedMessage savedMessage, LinkedList<byte[]> pics) {
        this.savedMessage = savedMessage;
        this.pics = pics;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
