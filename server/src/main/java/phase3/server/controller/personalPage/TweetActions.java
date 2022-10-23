package phase3.server.controller.personalPage;

import phase3.server.config.ReportNum;
import phase3.shared.events.personalPage.TweetActionEvent;
import phase3.shared.model.Retweet;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import phase3.shared.response.personalPage.TweetActionResponse;

public class TweetActions {
    public static TweetActionResponse tweetAction(TweetActionEvent event){
        User user = User.getUserByAuthToken(event.authToken);
        Tweet tweet = User.getUser(event.id).Tweets.get(Integer.parseInt(event.tweetId)-1);
        if (event.action.equals("like")){
            if (!tweet.likes.contains(user.Id)){
                tweet.likes.add(user.Id);
                return new TweetActionResponse(event.action,"Tweet Liked!",tweet.likes.size()+"");
            }
            return new TweetActionResponse(event.action,"Tweet is Already Liked!",tweet.likes.size()+"");
        }
        else if (event.action.equals("retweet")){
            user.addtweet(new Retweet(tweet, user.Id));
            return new TweetActionResponse(event.action, "Retweeted!", event.action);
        }
        else if (event.action.equals("report")){
            tweet.reportNumber++;
            if (tweet.reportNumber >= ReportNum.reportNum){
                User.getUser(tweet.Id).Tweets.remove(tweet);
                return new TweetActionResponse(event.action,"Tweet Removed!","Report");
            }
            return new TweetActionResponse(event.action,"Tweet Reported!","Report");
        }
        return null;
    }
}
