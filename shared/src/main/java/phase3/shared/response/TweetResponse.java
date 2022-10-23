package phase3.shared.response;

import phase3.shared.model.Tweet;

public class TweetResponse extends Response{

    public Tweet tweet;

    public TweetResponse(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
