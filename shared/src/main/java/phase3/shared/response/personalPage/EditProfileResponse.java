package phase3.shared.response.personalPage;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class EditProfileResponse extends Response {

    public String message;

    public EditProfileResponse(String message) {
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
