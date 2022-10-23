package phase3.shared.events;

import phase3.shared.response.Response;

public class GetTweetEvent extends Event{
    public String authToken;
    public String tweetId;
    public String id;

    public GetTweetEvent(String authToken, String tweetId, String id) {
        this.authToken = authToken;
        this.tweetId = tweetId;
        this.id = id;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getTweet(this);
    }
}
