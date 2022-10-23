package phase3.shared.response.personalPage;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class ShareTweetResponse extends Response {
    public String message;

    public ShareTweetResponse(String message) {
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
