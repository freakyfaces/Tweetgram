package phase3.client.view.explorer.search;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import phase3.client.config.AppImagesPathsConfig;
import phase3.client.config.explorer.ProfileConfig;
import phase3.client.view.MainApp;
import phase3.shared.events.explorer.ProfilePageActionsEvent;
import phase3.client.view.explorer.ExplorerPageController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import phase3.shared.response.explorer.GetProfileResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    ProfileConfig config;
    {
        try {
            config = new ProfileConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    AppImagesPathsConfig imagesConfig;

    {
        try {
            imagesConfig = new AppImagesPathsConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GetProfileResponse getProfileResponse;

    private final ProfileStringListener stringListener = new ProfileStringListener();
    public static ExplorerPageController controller;
    @FXML
    private Circle profilePic;
    @FXML
    private Button message;
    @FXML
    public Button follow;
    @FXML
    private Button report;
    @FXML
    public Button block;
    @FXML
    private Label name;
    @FXML
    private Label username;
    @FXML
    private Label lastSeen;
    @FXML
    private Label followState;
    @FXML
    private Label alert;
    @FXML
    private Label pageState;
    @FXML
    public void block() throws IOException{
        ProfilePageActionsEvent event = new ProfilePageActionsEvent(MainApp.controller.authToken,
                block.getText(),getProfileResponse.username);
        stringListener.StringEventOccurred(event, this);
    }
    @FXML
    public void report() throws IOException {
        ProfilePageActionsEvent event = new ProfilePageActionsEvent(MainApp.controller.authToken,
                report.getText(),getProfileResponse.username);
        stringListener.StringEventOccurred(event, this);
    }
    @FXML
    public void follow() throws IOException {
        ProfilePageActionsEvent event = new ProfilePageActionsEvent(MainApp.controller.authToken,
                follow.getText(),getProfileResponse.username);
        stringListener.StringEventOccurred(event, this);
    }
    @FXML
    public void message() throws IOException{
        /*
        String id = "";
        if (getProfileResponse.username.equals()){
            ExplorerPageController.message = true;
            controller.savedMessages();
            return;
        }

        else if (user.blacklist.contains(AuthController.user.Id)){
            alert.setText(config.youAreBlocked);
            return;
        }
        else if (user.pvs.contains(user.Id + AuthController.user.Id)){
            id = user.Id + AuthController.user.Id;
            ExplorerPageController.message = true;
            MessagingPageController.pvChat = Load.loadPvChat(id);
            controller.pvChat();
        }
        else if (user.pvs.contains(AuthController.user.Id + user.Id)){
            id = AuthController.user.Id + user.Id;
            ExplorerPageController.message = true;
            MessagingPageController.pvChat = Load.loadPvChat(id);
            controller.pvChat();
        }
        else if (id.equals("")){
            alert.setText(config.cantMessage);
        }

         */
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(getProfileResponse.name);
        username.setText(getProfileResponse.username);
        lastSeen.setText(getProfileResponse.lastSeen);
        followState.setText(getProfileResponse.followState);
        pageState.setText(getProfileResponse.pageState);
        block.setText(getProfileResponse.block);
        follow.setText(getProfileResponse.followBtn);
        Image image = new Image(new File(imagesConfig.account).toURI().toString(), false);
        if (getProfileResponse.profilePic != null){
            image= new Image(new ByteArrayInputStream(getProfileResponse.profilePic));
        }
        profilePic.setFill(new ImagePattern(image));
    }
    public void setAlert(String s){
        alert.setText(s);
    }


}
