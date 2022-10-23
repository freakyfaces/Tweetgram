package phase3.shared.response.setting.privacy;

import phase3.shared.events.setting.privacy.PrivacySettingEvent;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class PrivacySettingResponse extends Response {

    public String lastSeen;
    public String pageState;
    public String isActive;


    public PrivacySettingResponse(String lastSeen, String pageState, String isActive) {
        this.lastSeen = lastSeen;
        this.pageState = pageState;
        this.isActive = isActive;
    }


    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
