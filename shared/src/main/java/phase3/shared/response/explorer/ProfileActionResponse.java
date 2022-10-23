package phase3.shared.response.explorer;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class ProfileActionResponse extends Response {
    public String message;
    public String buttonText;

    public ProfileActionResponse(String message, String buttonText) {
        this.message = message;
        this.buttonText = buttonText;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
