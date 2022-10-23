package phase3.shared.response.setting.privacy;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class ChangePasswordResponse extends Response {
    public String message;
    public ChangePasswordResponse(String message) {
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }

}
