package phase3.shared.response.personalPage;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class TweetActionResponse extends Response {

    public String action;
    public String message;
    public String btnText;

    public TweetActionResponse(String action, String message, String btnText) {
        this.action = action;
        this.message = message;
        this.btnText = btnText;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
