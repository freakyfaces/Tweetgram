package phase3.shared.events.setting.privacy;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class PrivacySettingEvent extends Event {

    public String lastSeen;
    public String pageState;
    public String isActive;
    public String authToken;


    public PrivacySettingEvent(String lastSeen, String pageState, String isActive, String authToken) {
        this.lastSeen = lastSeen;
        this.pageState = pageState;
        this.isActive = isActive;
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.privacySetting(this);
    }
}
