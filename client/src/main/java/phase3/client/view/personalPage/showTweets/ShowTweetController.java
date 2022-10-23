package phase3.client.view.personalPage.showTweets;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import phase3.client.config.tweet.ShowTweetConfig;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import phase3.client.view.personalPage.PersonalPageStringListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.TweetResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowTweetController implements Initializable, ResponseVisitor<TweetResponse> {
    ShowTweetConfig config;

    {
        try {
            config = new ShowTweetConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public byte[] tweetPic;
    public byte[] profilePic;
    public static String status1;
    public ShowTweetStringListener showTweetStringListener = new ShowTweetStringListener();
    private Tweet tweet;
    public String pane ="";
    @FXML
    public TextField reciever;
    @FXML
    private Label likes;
    @FXML
    public Label status;
    @FXML
    private Button report;
    @FXML
    private Button showProfile;
    @FXML
    private Button comment;
    @FXML
    private Button showComments;
    @FXML
    public Button like;
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
    private Circle userProfile;
    @FXML
    public void report() throws IOException {
        showTweetStringListener.StringEventOccurred(tweet, "report");
    }
    @FXML
    public void showProfile() throws IOException {
        showTweetStringListener.StringEventOccurred(tweet, tweet.Id);
    }
    @FXML
    public void comment() throws IOException {
        showTweetStringListener.StringEventOccurred(tweet, "comment");
    }
    @FXML
    public void showComments() throws IOException {
        if (tweet.comments.size()>0){
            showTweetStringListener.StringEventOccurred(tweet, "showComments");
        }
        else{
            status.setText(config.noComments);
        }
    }
    @FXML
    public void like() throws IOException {
        showTweetStringListener.StringEventOccurred(tweet, "like");
    }
    @FXML
    public void retweet() throws IOException {
        showTweetStringListener.StringEventOccurred(tweet, "retweet");
        status.setText(config.retweetMessage);
    }
    @FXML
    public void messageThis() throws IOException {
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
        }

         */
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTweetStringListener.showTweetController = this;
        tweet = PersonalPageStringListener.tweet1;
        tweetPic = PersonalPageStringListener.tweet1Pic;
        profilePic = PersonalPageStringListener.profile1Pic;
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
        if (profilePic != null){
            Image image = new Image(new ByteArrayInputStream(profilePic));
            Platform.runLater(()->userProfile.setFill(new ImagePattern(image)));
        }
    }
    @Override
    public void visit(TweetResponse tweetResponse) {
        showData();
    }
}
