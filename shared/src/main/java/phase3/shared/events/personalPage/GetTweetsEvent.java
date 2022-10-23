package phase3.shared.events.personalPage;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.model.Tweet;
import phase3.shared.response.Response;

public class GetTweetsEvent extends Event {

    public String authToken;
    public Tweet subTweet;
    public GetTweetsEvent(String authToken, Tweet subTweet) {
        this.authToken = authToken;
        this.subTweet = subTweet;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getTweets(this);
    }
}
