package phase3.client.view.personalPage.editProfile;


import javafx.application.Platform;
import javafx.scene.paint.ImagePattern;
import phase3.client.config.AppImagesPathsConfig;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.EditProfileFormEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import phase3.shared.events.personalPage.ProfileInfoEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.ProfileInfoResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfilePageController implements Initializable, ResponseVisitor<ProfileInfoResponse> {
    AppImagesPathsConfig config;

    {
        try {
            config = new AppImagesPathsConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    EditProfileFormListener formListener = new EditProfileFormListener();
    public byte[] imageInByte = null;
    @FXML
    private Circle profilePic;
    @FXML
    private Button saveChanges;
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField number;
    @FXML
    private TextField birthDate;
    @FXML
    private TextField bio;
    @FXML
    private Label statusLabel;
    @FXML
    public void saveChanges(){
        EditProfileFormEvent formEvent = new EditProfileFormEvent(  username.getText(),name.getText(),
                email.getText(), number.getText(), birthDate.getText(), bio.getText() ,imageInByte
                , MainApp.controller.authToken);
        formListener.EventOccurred(this, formEvent);

    }
    @FXML
    public void chooseFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("JPG files (*.jpg)",
                 "*.jpg");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            Image myImage = new Image(file.toURI().toString(), false);
            profilePic.setFill(new javafx.scene.paint.ImagePattern(myImage));
            BufferedImage originalImage= ImageIO.read(file);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos );
            imageInByte = baos.toByteArray();
        }
    }
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
        Platform.runLater(()->birthDate.setText(profileInfoResponse.birthDate));
        Platform.runLater(()->bio.setText(profileInfoResponse.bio));
        Image image = new Image(new File(config.account).toURI().toString(), false);
        if (profileInfoResponse.profilePicture != null){
            image= new Image(new ByteArrayInputStream(profileInfoResponse.profilePicture));
        }
        profilePic.setFill(new ImagePattern(image));

    }

    public void setStatus(String s){
        statusLabel.setText(s);
    }
}
