package phase3.shared.response.authentication;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class SignUpResponse extends Response {

    public String authToken;
    public String message;
    public SignUpResponse(String authToken, String message) {

        this.authToken = authToken;
        this.message = message;

    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
