package phase3.client.view.timeline;

import javafx.application.Platform;
import javafx.scene.image.Image;
import phase3.client.config.tweet.ShowTweetConfig;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.TweetActionEvent;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.TweetActionResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimelineTweetController implements Initializable, ResponseVisitor<TweetActionResponse> {
    ShowTweetConfig config;
    {
        try {
            config = new ShowTweetConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TimelinePageController controller;
    public static String cm = "";
    private Tweet tweet;
    public static Tweet tweet1;
    public byte[] tweetPic;
    public byte[] tweetProfile;
    public String pane ="";
    @FXML
    private Label status;
    @FXML
    private Label likes;
    @FXML
    private Button report;
    @FXML
    private Button showProfile;
    @FXML
    private Button comment;
    @FXML
    private Button showComments;
    @FXML
    private Button like;
    @FXML
    private Button retweet;
    @FXML
    private Button messageThis;
    @FXML
    private Label tweetText;
    @FXML
    private Label tweetInfo;
    @FXML
    private ImageView tweetImg;
    @FXML
    private TextField reciever;
    @FXML
    private Circle userProfile;
    @FXML
    public void report(){
    }
    @FXML
    public void showProfile() throws IOException {
        //showTweetStringListener.StringEventOccurred(tweet, "showProfile");
    }
    @FXML
    public void comment(){
        cm = "Cm";
        tweet1 = tweet;
        controller.comment();
    }
    @FXML
    public void showComments(){
        if (tweet.comments.size()>0){
            TimelinePageController.tweets.add(tweet.comments);
            controller.showCms();
        }
        else{
            status.setText(config.noComments);
        }

    }
    @FXML
    public void like(){
        MainApp.controller.addEvent(new NewEvent(new TweetActionEvent(MainApp.controller.authToken,
                "like",tweet.Id, tweet.tweetId),this));
    }
    @FXML
    public void retweet(){
        MainApp.controller.addEvent(new NewEvent(new TweetActionEvent(MainApp.controller.authToken,
                "retweet",tweet.Id, tweet.tweetId),this));

    }
    @FXML
    public void messageThis() throws FileNotFoundException {
        /*
        String id = "";
        if (AuthController.user.username.equals(reciever.getText())){
            AuthController.user.savedMessage.saveMessage(tweet);
            status.setText(config.messageSent);
            return;
        }
        else if (AuthController.user.pvs.contains(User.username2id(reciever.getText())+AuthController.user.Id)){
            id = User.username2id(reciever.getText())+AuthController.user.Id;
        }
        else if (AuthController.user.pvs.contains(AuthController.user.Id+User.username2id(reciever.getText()))){
            id = AuthController.user.Id+User.username2id(reciever.getText());
        }
        if (id.equals("")){
            status.setText(config.cantSend);
        }
        else {
            try {
                Load.loadPvChat(id).sendMsg(tweet, AuthController.user.Id);
            } catch (IOException e) {
                e.printStackTrace();
            }
            status.setText(config.messageSent);
        }*/
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tweet = controller.tweet1;
        tweet1 = tweet;
        tweetProfile = controller.tweet1Profile;
        tweetPic = controller.tweet1Pic;
        showData();
    }
    public void showData(){
        Platform.runLater(()->like.setText(tweet.likes.size()+""));
        Platform.runLater(()->tweetInfo.setText(User.id2username(tweet.Id) +config.tweetInfo+tweet.datetime));
        Platform.runLater(()->tweetText.setText(tweet.text));
        if (tweetPic != null){
            Image image = new Image(new ByteArrayInputStream(tweetPic));
            Platform.runLater(()->tweetImg.setImage(image));
        }
        if (tweetProfile != null){
            Image image = new Image(new ByteArrayInputStream(tweetProfile));
            Platform.runLater(()->tweetImg.setImage(image));
        }
    }

    @Override
    public void visit(TweetActionResponse tweetActionResponse) {
        if (tweetActionResponse.action.equals("retweet")){
            Platform.runLater(()->status.setText(config.retweetMessage));
        }
        else if (tweetActionResponse.action.equals("like")){
            Platform.runLater(()->like.setText(tweetActionResponse.btnText));
            Platform.runLater(()->status.setText(tweetActionResponse.message));
        }
    }
}
