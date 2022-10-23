package phase3.shared.events.personalPage;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.model.Tweet;
import phase3.shared.response.Response;

public class TweetActionEvent extends Event {
    public String authToken;
    public String action;
    public String id;
    public String tweetId;
    public TweetActionEvent(String authToken, String action, String id, String tweetId) {
        this.authToken = authToken;
        this.action = action;
        this.id = id;
        this.tweetId = tweetId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.tweetAction(this);
    }
}
