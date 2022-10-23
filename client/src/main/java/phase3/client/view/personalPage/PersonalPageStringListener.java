package phase3.client.view.personalPage;

import javafx.application.Platform;
import phase3.client.listener.StringListener;
import phase3.shared.model.Tweet;
import phase3.client.view.LoadFXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import phase3.client.view.MainApp;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;

public class PersonalPageStringListener implements StringListener<BorderPane> {
    private LoadFXML loadFXML = new LoadFXML();
    public static Tweet tweet1 ;
    public static byte[] tweet1Pic;
    public static byte[] profile1Pic;
    public static LinkedList<LinkedList<byte[]>> tweetsPic;
    public static LinkedList<LinkedList<Tweet>> tweets;
    public static LinkedList<LinkedList<byte[]>> profilesPic;
    @Override
    public void StringEventOccurred(BorderPane borderPane, String string) throws IOException {
        if (string.equals("info")){
            borderPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.profileInfoPath)));
        }
        else if (string.equals("editProfile")){
            borderPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.editProfilePath)));
        }
        else if (string.equals("notifications")){
            borderPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.notificationsPagePath)));
        }
        else if (string.equals("tweet")) {
            borderPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.personalPageTweetPath)));
        }
        else if (string.equals("showTweets")){
            ScrollPane scrollPane = new ScrollPane();
            borderPane.setCenter(scrollPane);
            VBox vBox = new VBox();
            scrollPane.setContent(vBox);
            scrollPane.setFitToWidth(true);
            vBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
            scrollPane.setFitToWidth(true);
            for (int i = 0; i < tweets.getLast().size(); i++) {
                tweet1 = tweets.getLast().get(i);
                tweet1Pic = tweetsPic.getLast().get(i);
                profile1Pic = profilesPic.getLast().get(i);
                vBox.getChildren().add((new HBox(loadFXML.loadFXMl(MainApp.paths.personalPageShowTweetPath))));
            }
        }
        else if (string.equals("lists")){
            borderPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.listsPath)));
        }
    }
}
