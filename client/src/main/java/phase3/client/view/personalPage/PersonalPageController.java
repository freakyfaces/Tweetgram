package phase3.client.view.personalPage;

import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.LoadFXML;
import phase3.client.view.MainApp;
import phase3.client.view.explorer.ExplorerStringListener;
import phase3.client.view.personalPage.showTweets.ShowTweetStringListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import phase3.shared.events.personalPage.GetTweetsEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.GetTweetsResponse;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PersonalPageController implements Initializable, ResponseVisitor<GetTweetsResponse> {
    LoadFXML loadFXML = new LoadFXML();
    PersonalPageStringListener stringListener = new PersonalPageStringListener();
    ExplorerStringListener explorerStringListener = new ExplorerStringListener();
    public int lvl;
    @FXML
    private Label state;
    @FXML
    private Button tweet;
    @FXML
    private Button showTweets;
    @FXML
    private Button info;
    @FXML
    private Button editProfile;
    @FXML
    private Button notifications;
    @FXML
    private Button lists;
    @FXML
    private BorderPane borderPane;
    @FXML
    public void info() throws IOException {

        stringListener.StringEventOccurred(borderPane, "info");
    }
    @FXML
    public void editProfile() throws IOException {
        stringListener.StringEventOccurred(borderPane, "editProfile");
    }
    @FXML
    public void notifications() throws IOException {
        stringListener.StringEventOccurred(borderPane, "notifications");
    }
    @FXML
    public void tweet() throws IOException {
        stringListener.StringEventOccurred(borderPane, "tweet");
    }
    @FXML
    public void showTweets() throws IOException{
        MainApp.controller.addEvent(new NewEvent(new GetTweetsEvent(MainApp.controller.authToken, null)
                ,this));

    }
    @FXML
    public void lists() throws IOException{
        stringListener.StringEventOccurred(borderPane, "lists");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ShowTweetStringListener.personalController = this;
        lvl = 0;

    }
    public void showCms() throws IOException{
        MainApp.controller.addEvent(new NewEvent(new GetTweetsEvent(MainApp.controller.authToken,
                ShowTweetStringListener.tweet1), this));
    }
    @FXML
    private Button back;
    @FXML
    public void back() throws IOException {
        if (PersonalPageStringListener.tweets != null){
            if (PersonalPageStringListener.tweets.size() > 1) {
                PersonalPageStringListener.tweets.removeLast();
                PersonalPageStringListener.profilesPic.removeLast();
                PersonalPageStringListener.tweetsPic.removeLast();
                stringListener.StringEventOccurred(borderPane, "showTweets");
            }
        }
    }
    public void goToProfile(String username) throws IOException {
        explorerStringListener.StringEventOccurred(borderPane,username,null);
    }

    @Override
    public void visit(GetTweetsResponse getTweetsResponse) {
        if (PersonalPageStringListener.tweets == null){
            PersonalPageStringListener.tweets = new LinkedList<>();
            PersonalPageStringListener.tweetsPic = new LinkedList<>();
            PersonalPageStringListener.profilesPic = new LinkedList<>();
        }
        PersonalPageStringListener.tweets.add(getTweetsResponse.tweets);
        PersonalPageStringListener.tweetsPic.add(getTweetsResponse.tweetsPic);
        PersonalPageStringListener.profilesPic.add(getTweetsResponse.profilePic);
        lvl++;
        if (PersonalPageStringListener.tweets != null){
            if (PersonalPageStringListener.tweets.size() > 0) {
                    Platform.runLater(()-> {
                        try {
                            stringListener.StringEventOccurred(borderPane, "showTweets");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            }
            else {
                borderPane.getChildren().clear();
                borderPane.setCenter(state);
                state.setText("Theres no tweets here.....");
            }
        }
    }
    public void showCurrentTweets() throws IOException {
        stringListener.StringEventOccurred(borderPane, "showTweets");
    }
}
