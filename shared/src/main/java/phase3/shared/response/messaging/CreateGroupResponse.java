package phase3.shared.response.messaging;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class CreateGroupResponse extends Response {
    public String message;

    public CreateGroupResponse(String message) {
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
