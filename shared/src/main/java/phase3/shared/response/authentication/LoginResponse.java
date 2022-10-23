package phase3.shared.response.authentication;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class LoginResponse extends Response {

    public String message;
    public String authToken;

    public LoginResponse(String message, String authToken) {
        this.message = message;
        this.authToken = authToken;
    }


    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
