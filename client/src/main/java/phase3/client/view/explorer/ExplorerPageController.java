package phase3.client.view.explorer;

//import phase3.server.controller.explorer.ExploreController;
import phase3.shared.model.Tweet;
import phase3.client.view.MainApp;
import phase3.client.view.explorer.explore.ExploreTweetController;
import phase3.client.view.LoadFXML;
import phase3.client.view.explorer.search.ProfileController;
import phase3.client.view.personalPage.showTweets.ShowTweetStringListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import phase3.shared.util.Loop;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ExplorerPageController implements Initializable {
    LoadFXML loadFXML = new LoadFXML();
    public ExplorerStringListener explorerStringListener = new ExplorerStringListener();
    public static String searchStatus = "";
    public Tweet tweet1;
    public static String cm = "";
    public static boolean message = false;
    public static LinkedList<LinkedList<Tweet>> tweets;
    @FXML
    private Button search;
    @FXML
    private Button explore;
    @FXML
    private Label status;
    @FXML
    private TextField searchTextField;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button back;
    @FXML
    public void back() throws IOException {
        if (message){
            message = false;
            search();
        }
        else if (tweets.size() > 1){
            tweets.removeLast();
            showCms();
        }
    }
    @FXML
    public void search() throws IOException {
        Loop loop = new Loop(2,()-> {
            try {
                explorerStringListener.StringEventOccurred(borderPane, searchTextField.getText()
                        , this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loop.start();


    }
    @FXML
    public void explorer(){
        showTweets();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProfileController.controller = this;
        tweets = new LinkedList<>();
        ShowTweetStringListener.exploreController = this;
        ExploreTweetController.controller = this;
    }
    public void tweet(){
        borderPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.personalPageTweetPath)));
    }
    public void showTweets(){
        ScrollPane scrollPane = new ScrollPane();
        borderPane.setCenter(scrollPane);
        VBox vBox = new VBox();
        tweets = new LinkedList<>();
        //tweets.add(ExploreController.explore());
        scrollPane.setContent(vBox);
        vBox.getChildren().clear();
        for (Tweet tweet : tweets.getLast()) {
            tweet1 = tweet;
            vBox.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.exploreTweetPath)));
        }
    }

    public void showCms(){
        ScrollPane scrollPane = new ScrollPane();
        borderPane.setCenter(scrollPane);
        VBox vBox = new VBox();
        scrollPane.setContent(vBox);
        for (Tweet tweet : tweets.getLast()) {
            tweet1 = tweet;
            vBox.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.exploreTweetPath)));
        }
    }
    public void comment(){
        ScrollPane scrollPane = new ScrollPane();
        borderPane.setCenter(scrollPane);
        VBox vBox = new VBox();
        scrollPane.setContent(vBox);
        vBox.getChildren().add(loadFXML.loadFXMl(MainApp.paths.personalPageTweetPath));
    }
    public void pvChat(){
        borderPane.getChildren().clear();
        borderPane.setCenter(loadFXML.loadFXMl(MainApp.paths.chatPagePath));
    }
    public void savedMessages(){
        borderPane.getChildren().clear();
        borderPane.setCenter(loadFXML.loadFXMl(MainApp.paths.savedMessagePagePath));
    }
    public void setStatus(String s){
        status.setText(s);
    }
}
