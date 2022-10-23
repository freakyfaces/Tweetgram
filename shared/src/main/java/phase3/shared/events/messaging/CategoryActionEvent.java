package phase3.shared.events.messaging;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class CategoryActionEvent extends Event {

    public String authToken;
    public String username;
    public String categoryName;
    public String message;

    public CategoryActionEvent(String authToken, String username, String categoryName, String message) {
        this.authToken = authToken;
        this.username = username;
        this.categoryName = categoryName;
        this.message = message;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.categoryAction(this);
    }
}
