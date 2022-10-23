package phase3.shared.events.personalPage;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.model.Tweet;
import phase3.shared.response.Response;

public class ShareTweetEvent extends Event {
    public String authToken;
    public byte[] photo;
    public String text;
    public Tweet subTweet;


    public ShareTweetEvent(String authToken, byte[] photo, String text, Tweet subTweet) {
        this.authToken = authToken;
        this.photo = photo;
        this.text = text;
        this.subTweet = subTweet;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.shareTweet(this);
    }
}
