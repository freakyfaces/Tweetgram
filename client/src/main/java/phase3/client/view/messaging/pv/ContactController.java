package phase3.client.view.messaging.pv;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import phase3.client.view.messaging.MessagingPageController;
import phase3.shared.model.messaging.PvChatOverview;
import phase3.shared.model.messaging.pvChat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactController implements Initializable {
    private PvChatOverview pvChat ;
    @FXML
    private Circle profilePic;
    @FXML
    private Label lastMessage;
    @FXML
    private Label unseen;
    @FXML
    private Label name;
    @FXML
    public Pane pane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pvChat = MessagingPageController.pvChat;
        unseen.setText(pvChat.unseen);
        lastMessage.setText(pvChat.shown);
        name.setText(pvChat.name);
        if (pvChat.profile != null){
            Image image= new Image(new ByteArrayInputStream(pvChat.profile));
            profilePic.setFill(new ImagePattern(image));
        }


    }
}
