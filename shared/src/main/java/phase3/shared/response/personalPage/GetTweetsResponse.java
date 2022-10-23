package phase3.shared.response.personalPage;

import phase3.shared.model.Tweet;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;
import java.util.LinkedList;

public class GetTweetsResponse extends Response {

    public LinkedList<Tweet> tweets;
    public LinkedList<byte[]> tweetsPic;
    public LinkedList<byte[]> profilePic;

    public GetTweetsResponse(LinkedList<Tweet> tweets, LinkedList<byte[]> tweetsPic, LinkedList<byte[]> profilePic) {
        this.tweets = tweets;
        this.tweetsPic = tweetsPic;
        this.profilePic = profilePic;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
