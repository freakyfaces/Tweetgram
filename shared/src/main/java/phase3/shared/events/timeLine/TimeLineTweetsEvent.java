package phase3.shared.events.timeLine;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

public class TimeLineTweetsEvent extends Event {
    public String authToken;

    public TimeLineTweetsEvent(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.timeLineTweets(this);
    }
}
