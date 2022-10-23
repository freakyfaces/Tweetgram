package phase3.client.view.messaging.pv;


import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.messaging.PvMessageActionEvent;
import phase3.shared.model.messaging.Message;
import phase3.shared.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {
    private Message message;
    public static ChatPageController chatPageController;
    public byte[] messagePic;
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @FXML
    private Label giver;
    @FXML
    private TextField text;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private Label time;
    @FXML
    private ImageView imageView;
    @FXML
    public void delete(){
        MainApp.controller.addEvent(new NewEvent(new PvMessageActionEvent(MainApp.controller.authToken
                , ChatPageController.pv.id,"delete","",message.id,null),null));
    }
    @FXML
    public void edit(){
        MainApp.controller.addEvent(new NewEvent(new PvMessageActionEvent(MainApp.controller.authToken,
                ChatPageController.pv.id, "edit",text.getText(),message.id,null),null));

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMessage(ChatPageController.message);
        giver.setText(message.giver+ " : ");
        text.setText(message.text);
        messagePic = ChatPageController.messagePic;
        time.setText(message.dateTime);
        if (messagePic != null){
            Image image = new Image(new ByteArrayInputStream(messagePic));
            imageView.setImage(image);
        }
    }
}
