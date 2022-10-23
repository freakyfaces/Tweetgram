package phase3.client.view.explorer.explore;

import phase3.client.config.tweet.ShowTweetConfig;
//import phase3.server.controller.authentication.AuthController;
//import phase3.server.dataBase.Load;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import phase3.client.view.explorer.ExplorerPageController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExploreTweetController implements Initializable {
    ShowTweetConfig config;
    {
        try {
            config = new ShowTweetConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExplorerPageController controller;
    public static String cm = "";
    private Tweet tweet;
    public static Tweet tweet1;
    public String pane ="";
    @FXML
    private TextField reciever;
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
    private Circle userProfile;
    @FXML
    public void report(){
        User.getUser(tweet.Id).Tweets.get(User.getUser(tweet.Id).Tweets.indexOf(tweet)).reportNumber++;
        if (tweet.reportNumber >= config.reportNumber){
            User.getUser(tweet.Id).Tweets.remove(tweet);
            ExplorerPageController.tweets.getLast().remove(tweet);
            controller.showTweets();
        }
        status.setText(config.reportMessage);
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
            ExplorerPageController.tweets.add(tweet.comments);
            controller.showCms();
        }
        else{
            status.setText(config.noComments);
        }

    }
    @FXML
    public void like(){/*
        if (tweet.likes.contains(AuthController.user.Id)){
            status.setText(shared.config.likedMessage);

        }
        else {
            tweet.likes.add(AuthController.user.Id);
            like.setText(tweet.likes.size()+"");
        }*/
    }
    @FXML
    public void retweet(){
    //    AuthController.user.Tweets.add(new Retweet(tweet,AuthController.user.Id));
        status.setText(config.retweetMessage);
    }
    @FXML
    public void messageThis() throws FileNotFoundException {
        String id = "";/*
        if (AuthController.user.username.equals(reciever.getText())){
            AuthController.user.savedMessage.saveMessage(tweet);
            status.setText(shared.config.messageSent);
            return;
        }
        else if (AuthController.user.pvs.contains(User.username2id(reciever.getText())+AuthController.user.Id)){
            id = User.username2id(reciever.getText())+AuthController.user.Id;
        }
        else if (AuthController.user.pvs.contains(AuthController.user.Id+User.username2id(reciever.getText()))){
            id = AuthController.user.Id+User.username2id(reciever.getText());
        }
        if (id.equals("")){
            status.setText(shared.config.cantSend);
        }
        else {
            try {
                Load.loadPvChat(id).sendMsg(tweet, AuthController.user.Id);
            } catch (IOException e) {
                e.printStackTrace();
            }
            status.setText(shared.config.messageSent);
        }
        */
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tweet = controller.tweet1;
        tweet1 = tweet;
        //Load.loadProfile(userProfile, tweet.Id);
        showData();
    }
    public void showData(){
        like.setText(tweet.likes.size()+"");
        tweetText.setText(tweet.text);
        tweetInfo.setText(User.id2username(tweet.Id) +config.tweetInfo+tweet.datetime);
        /*File file = new File (shared.config.dataFolder+ AuthController.user.Id+shared.config.tweetFolder+tweet.tweetId+shared.config.tweetImgFormat);
        if (file.exists()){
            tweetImg.setImage(new Image(file.toURI().toString()));
        }
        else{
            tweetImg.setImage(null);
        }
         */
    }
}
