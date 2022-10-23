package phase3.client.view.messaging.groups;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import phase3.shared.model.messaging.GroupChatOverview;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupItemController implements Initializable {
    private GroupChatOverview groupChat;
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
        groupChat = GroupOverviewController.groupChatOverview;
        String shown = "";
        for (String user : groupChat.users) {
            shown += user+" ";
        }
        name.setText(groupChat.name);
        unseen.setText(groupChat.unseen);
        lastMessage.setText(shown);
    }
}
