package phase3.shared.events.setting.privacy;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;




public class ChangePasswordEvent extends Event {
    public String oldPass;
    public String newPass1;
    public String newPass2;
    public String authToken;

    public ChangePasswordEvent(String oldPass, String newPass1, String newPass2, String authToken) {
        this.oldPass = oldPass;
        this.newPass1 = newPass1;
        this.newPass2 = newPass2;
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.changePassword(this);
    }
}
