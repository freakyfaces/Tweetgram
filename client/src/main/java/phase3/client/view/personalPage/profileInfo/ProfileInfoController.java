package phase3.client.view.personalPage.profileInfo;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import phase3.client.config.AppImagesPathsConfig;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.ProfileInfoEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.ProfileInfoResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileInfoController implements Initializable, ResponseVisitor<ProfileInfoResponse> {
    AppImagesPathsConfig config;

    {
        try {
            config = new AppImagesPathsConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label name;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label number;
    @FXML
    private Label birthdate;
    @FXML
    private Label bio;
    @FXML
    private Circle profilePicture;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        NewEvent event = new NewEvent(new ProfileInfoEvent(MainApp.controller.authToken), this);
        MainApp.controller.addEvent(event);

    }

    @Override
    public void visit(ProfileInfoResponse profileInfoResponse) {

        Platform.runLater(()->name.setText(profileInfoResponse.name));
        Platform.runLater(()->username.setText(profileInfoResponse.username));
        Platform.runLater(()->email.setText(profileInfoResponse.email));
        Platform.runLater(()->number.setText(profileInfoResponse.phoneNumber));
        Platform.runLater(()->birthdate.setText(profileInfoResponse.birthDate));
        Platform.runLater(()->bio.setText(profileInfoResponse.bio));
        Image image = new Image(new File(config.account).toURI().toString(), false);
        if (profileInfoResponse.profilePicture != null){
                image= new Image(new ByteArrayInputStream(profileInfoResponse.profilePicture));
        }
        profilePicture.setFill(new ImagePattern(image));

    }
}
