package phase3.shared.response.explorer;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class GetProfileResponse extends Response {

    public byte[] profilePic;
    public String name;
    public String username;
    public String lastSeen;
    public String followState;
    public String block;
    public String followBtn;
    public String message;
    public String pageState;

    public GetProfileResponse(byte[] profilePic, String name, String username, String lastSeen, String followState
            , String block, String followBtn, String pageState) {
        this.profilePic = profilePic;
        this.pageState = pageState;
        this.name = name;
        this.username = username;
        this.lastSeen = lastSeen;
        this.followState = followState;
        this.block = block;
        this.followBtn = followBtn;
        this.message = null;
    }
    public GetProfileResponse(String message){
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
