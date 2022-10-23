package phase3.server.controller.personalPage;

import phase3.server.controller.Controller;
import phase3.server.dataBase.Save;
import phase3.shared.events.personalPage.ShareTweetEvent;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import phase3.shared.response.personalPage.ShareTweetResponse;

import java.io.IOException;

public class ShareTweetController extends Controller {
    public static ShareTweetResponse ShareTweet(ShareTweetEvent event) throws IOException {
        User user = User.getUserByAuthToken(event.authToken);
        if (event.subTweet == null){
            user.addtweet(new Tweet(event.text, user.Id));
            Save.saveTweetPic(event.photo, user.Id);
        }
        else{
            User.getUser(event.subTweet.Id).Tweets.get(Integer.parseInt(event.subTweet.tweetId)-1)
                    .comments.add(new Tweet(event.text, user.Id));
        }
        return new ShareTweetResponse("Tweet is Now Shared!");
    }

}
