package phase3.server.controller.explorer;

import phase3.server.controller.authentication.Login;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import java.util.LinkedList;

public class ExploreController {

    public static LinkedList<Tweet> exploreTweets = new LinkedList<>();

    public static LinkedList<Tweet> explore(){
        for (User user : User.userList) {
            if (!Login.user.following.contains(user.Id) && !Login.user.Id.equals(user.Id)){
                Tweet tweet = new Tweet("ah", "0");
                int max = 0;
                for (Tweet tweet1 : user.Tweets) {
                    if (tweet1.likes.size() >= max && User.getUser(tweet1.Id).isactive &&
                            !User.getUser(tweet1.Id).pageState.equals("private")){
                        tweet = tweet1;
                        max = tweet1.likes.size();
                    }
                }
                if (!(tweet.text.equals("ah") && tweet.Id.equals("0")) && !exploreTweets.contains(tweet)){
                    exploreTweets.add(tweet);
                }
            }
        }
        return exploreTweets;
    }
}
