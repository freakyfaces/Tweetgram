package phase3.client.view.personalPage.lists;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import phase3.client.config.AppImagesPathsConfig;
import phase3.client.view.MainApp;
import phase3.shared.events.explorer.ProfilePageActionsEvent;
import phase3.client.view.explorer.search.ProfileStringListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListsProfileController implements Initializable {
    ProfileStringListener listener = new ProfileStringListener();
    public static String kind = "";
    public static String username1 = "";
    public byte[] profile1;
    AppImagesPathsConfig config;
    {
        try {
            config = new AppImagesPathsConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button function;
    @FXML
    private Circle profile;
    @FXML
    private Label username;
    @FXML
    public void func() throws IOException {
        listener.StringEventOccurred(new ProfilePageActionsEvent(MainApp.controller.authToken,
                function.getText(),username.getText()), null);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username1 = ListsController.username;
        kind = ListsController.kind;
        profile1 = ListsController.profile;
        Image image = new Image(new File(config.account).toURI().toString(), false);
        if (profile1 != null){
            image= new Image(new ByteArrayInputStream(profile1));
        }
        profile.setFill(new ImagePattern(image));
        username.setText(username1);
        if (kind.equals("blacklist")){
            function.setText("Unblock");
        }
        else {
            if (ListsController.response.followings.contains(username1)){
               function.setText("Unfollow");
            }
            else {
                function.setText("Follow");
            }
        }
    }
}
