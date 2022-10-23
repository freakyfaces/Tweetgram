package phase3.shared.events;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public abstract class Event {


    public abstract Response visit(EventVisitor eventVisitor);
}
