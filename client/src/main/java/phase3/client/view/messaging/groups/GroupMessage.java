package phase3.client.view.messaging.groups;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.messaging.GroupMessageActionEvent;
import phase3.shared.model.messaging.Message;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupMessage implements Initializable {
    private Message message;
    public static GroupPageController controller;
    public String groupId;
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
        MainApp.controller.addEvent(new NewEvent(new GroupMessageActionEvent(MainApp.controller.authToken
                , groupId ,"delete","",message.id,null),null));
    }
    @FXML
    public void edit(){
        MainApp.controller.addEvent(new NewEvent(new GroupMessageActionEvent(MainApp.controller.authToken,
                groupId, "edit",text.getText(),message.id,null),null));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupId = GroupPageController.groupId;
        setMessage(GroupPageController.message);
        giver.setText(message.giver+" : ");
        text.setText(message.text);
        time.setText(message.dateTime);
        messagePic = GroupPageController.messagePic;
        if (messagePic != null){
            Image image = new Image(new ByteArrayInputStream(messagePic));
            imageView.setImage(image);
        }
    }
}
