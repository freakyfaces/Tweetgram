package phase3.client.view.messaging.saved;


import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.messaging.GetSavedMessageEvent;
import phase3.shared.events.messaging.SavedMessageActionEvent;
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
import java.net.URL;
import java.util.ResourceBundle;

public class SMessageController implements Initializable {
    private Message message;
    public static SavedMessageController controller ;
    public Message getMessage() {
        return message;
    }
    public byte[] messagePic;
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
        MainApp.controller.addEvent(new NewEvent(new SavedMessageActionEvent(MainApp.controller.authToken,
                "delete","",message.id,null),null));
    }
    @FXML
    public void edit(){
        MainApp.controller.addEvent(new NewEvent(new SavedMessageActionEvent(MainApp.controller.authToken,
                "edit",text.getText(),message.id,null),null));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMessage(SavedMessageController.message);
        messagePic = SavedMessageController.messagePic;
        giver.setText(User.id2username(message.giver)+" :  ");
        text.setText(message.text);
        time.setText(message.dateTime);
        if (messagePic != null){
            Image image = new Image(new ByteArrayInputStream(messagePic));
            imageView.setImage(image);
        }
    }
}
