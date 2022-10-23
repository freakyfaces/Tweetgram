package phase3.client.view.timeline;


import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.shared.events.timeLine.TimeLineTweetsEvent;
import phase3.shared.model.Tweet;
import phase3.client.view.LoadFXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import phase3.client.view.MainApp;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.GetTweetsResponse;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TimelinePageController implements Initializable, ResponseVisitor<GetTweetsResponse> {
    LoadFXML loadFXML = new LoadFXML();
    public static String searchStatus = "";
    public Tweet tweet1;
    public byte[] tweet1Pic;
    public byte[] tweet1Profile;
    public static String cm = "";
    public static LinkedList<LinkedList<Tweet>> tweets;
    public static LinkedList<LinkedList<byte[]>> tweetPics;
    public static LinkedList<LinkedList<byte[]>> tweetProfiles;
    @FXML
    private Label label;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button back;
    @FXML
    public void back() {
        if (tweets.size() > 1) {
            tweets.removeLast();
            tweetPics.removeLast();
            tweetProfiles.removeLast();
            showCms();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TimelineTweetController.controller = this;
        tweets = new LinkedList<>();
        tweetPics = new LinkedList<>();
        tweetProfiles = new LinkedList<>();
        MainApp.controller.addEvent(new NewEvent(new TimeLineTweetsEvent(MainApp.controller.authToken),
                this));

    }
    public void tweet(){
        borderPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.personalPageTweetPath)));
    }
    public void showTweets(){
        ScrollPane scrollPane = new ScrollPane();
        borderPane.setCenter(scrollPane);
        VBox vBox = new VBox();
        scrollPane.setContent(vBox);
        vBox.getChildren().clear();
        for (int i = 0; i < tweets.getLast().size(); i++) {
            tweet1 = tweets.getLast().get(i);
            tweet1Pic = tweetPics.getLast().get(i);
            tweet1Profile = tweetProfiles.getLast().get(i);
            vBox.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.timelineTweetPath)));
        }
    }

    public void showCms(){
        borderPane.getChildren().clear();
        ScrollPane scrollPane = new ScrollPane();
        borderPane.setCenter(scrollPane);
        VBox vBox = new VBox();
        scrollPane.setContent(vBox);
        for (Tweet tweet : tweets.getLast()) {
            tweet1 = tweet;
            vBox.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.timelineTweetPath)));
        }
    }
    public void comment(){
        ScrollPane scrollPane = new ScrollPane();
        borderPane.setCenter(scrollPane);
        VBox vBox = new VBox();
        scrollPane.setContent(vBox);
        vBox.getChildren().add(loadFXML.loadFXMl(MainApp.paths.personalPageTweetPath));
    }

    @Override
    public void visit(GetTweetsResponse getTweetsResponse) {
        tweets.add(getTweetsResponse.tweets);
        tweetProfiles.add(getTweetsResponse.profilePic);
        tweetPics.add(getTweetsResponse.tweetsPic);
        if (tweets.getLast().size() == 0){
            Platform.runLater(()->label.setText("Oops There is no Tweets here!"));
        }
        else {
            Platform.runLater(()->showTweets());
        }
    }
}
