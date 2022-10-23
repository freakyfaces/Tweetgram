package phase3.client.view.personalPage.tweet;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import phase3.client.view.MainApp;
import phase3.client.view.explorer.explore.ExploreTweetController;
import phase3.client.view.personalPage.showTweets.ShowTweetStringListener;
import phase3.client.view.timeline.TimelineTweetController;
import phase3.shared.events.personalPage.ShareTweetEvent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TweetController implements Initializable {
    byte[] image;
    TweetStringListener stringListener = new TweetStringListener();
    @FXML
    private Button uploadImg;
    @FXML
    private Button shareTweet;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField tweetText;
    @FXML
    private Label state;
    @FXML
    public void uploadImg() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("JPG files (*.jpg)",
                "*.jpg");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            Image myImage = new Image(file.toURI().toString(), false);
            imageView.setImage(myImage);
            BufferedImage originalImage= ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", byteArrayOutputStream );
            image = byteArrayOutputStream.toByteArray();
        }
    }
    @FXML
    public void shareTweet() throws IOException {
        ShareTweetEvent event;
        if (ShowTweetStringListener.cm.equals("Cm")){
            event = new ShareTweetEvent(MainApp.controller.authToken, image, tweetText.getText(),
                            ShowTweetStringListener.tweet1);
            stringListener.StringEventOccurred(this, event);
            ShowTweetStringListener.personalController.showCurrentTweets();
            ShowTweetStringListener.cm = "";
        }
        if (ExploreTweetController.cm.equals("Cm")){
            event = new ShareTweetEvent(MainApp.controller.authToken, image, tweetText.getText()
                            , ExploreTweetController.tweet1);
            stringListener.StringEventOccurred(this, event);
            ExploreTweetController.controller.showCms();
            ExploreTweetController.cm = "";
        }
        if (TimelineTweetController.cm.equals("Cm")){
            event = new ShareTweetEvent(MainApp.controller.authToken, image, tweetText.getText(),
                    TimelineTweetController.tweet1);
            stringListener.StringEventOccurred(this, event);
            TimelineTweetController.controller.showCms();
            TimelineTweetController.cm = "";
        }
        else{
            event = new ShareTweetEvent(MainApp.controller.authToken, image, tweetText.getText(), null);
        }
        stringListener.StringEventOccurred(this, event);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TweetStringListener.test = "test";
    }
    public void setStatus(String s){
        state.setText(s);
    }
}
