package phase3.client.view.personalPage.showTweets;


import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.TweetActionEvent;
import phase3.shared.model.Tweet;
import phase3.client.view.explorer.ExplorerPageController;
import phase3.client.view.LoadFXML;
import phase3.client.view.personalPage.PersonalPageController;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.TweetActionResponse;
import java.io.IOException;

public class ShowTweetStringListener implements ResponseVisitor<TweetActionResponse>{
    public static PersonalPageController personalController ;
    public static ExplorerPageController exploreController;
    public ShowTweetController showTweetController;
    public static String cm = "";
    public static Tweet tweet1;
    private LoadFXML loadFXML = new LoadFXML();

    public void StringEventOccurred(Tweet tweet, String string) throws IOException {
        tweet1 = tweet;
        if(string.equals("like")){
            MainApp.controller.addEvent(new NewEvent(new TweetActionEvent(MainApp.controller.authToken,
                    "like",tweet.Id, tweet.tweetId),this));
        }
        else if(string.equals("comment")){
            tweet1 = tweet;
            cm = "Cm";
            if (personalController != null){
                personalController.tweet();
            }
            else {
                exploreController.tweet();
            }
        }
        else if(string.equals("retweet")){
            MainApp.controller.addEvent(new NewEvent(new TweetActionEvent(MainApp.controller.authToken,
                    "retweet",tweet.Id, tweet.tweetId),this));
        }
        else if(string.equals("showComments")){
                personalController.showCms();
        }
        else if(string.equals("messageThis")){
            MainApp.controller.addEvent(new NewEvent(new TweetActionEvent(MainApp.controller.authToken,
                    "message"+" "+showTweetController.reciever,tweet.Id, tweet.tweetId),this));
        }
        else if (string.equals("report")){
            MainApp.controller.addEvent(new NewEvent(new TweetActionEvent(MainApp.controller.authToken,
                    "report",tweet.Id, tweet.tweetId),this));
        }
        else{
            personalController.goToProfile(string);
        }
    }

    @Override
    public void visit(TweetActionResponse response) {
        if (response.action.equals("like")){
            Platform.runLater(()->showTweetController.like.setText(response.btnText));
            Platform.runLater(()->showTweetController.status.setText(response.message));
        }
        else if (response.action.equals("retweet")){
            Platform.runLater(()->showTweetController.status.setText(response.message));
        }
        else if (response.action.equals("report")){
            Platform.runLater(()->showTweetController.status.setText(response.message));
        }
    }
}
