package phase3.client.view.messaging.groups;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import phase3.client.model.NewEvent;
import phase3.shared.events.messaging.CreateGroupEvent;
import phase3.shared.events.messaging.GetGroupsEvent;
import phase3.client.view.LoadFXML;
import phase3.client.view.MainApp;
import phase3.shared.model.messaging.GroupChatOverview;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.messaging.GetGroupsResponse;
import phase3.shared.util.Loop;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupOverviewController implements Initializable, ResponseVisitor<GetGroupsResponse> {
    LoadFXML loadFXML = new LoadFXML();
    public static String groupChat;
    public static GroupChatOverview groupChatOverview;
    public GroupOverviewListener listener;
    public GetGroupsResponse response;
    @FXML
    private VBox groups;
    @FXML
    private Button create;
    @FXML
    private TextField groupName;
    @FXML
    private TextField memberUsername;
    @FXML
    public Label state;
    @FXML
    private Pane pane;
    @FXML
    public void createGroup(){
        listener.EventOccurred(new CreateGroupEvent(MainApp.controller.authToken, memberUsername.getText(),
                groupName.getText()), this);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listener = new GroupOverviewListener();
        Loop loop = new Loop(1,()->MainApp.controller.addEvent(
                new NewEvent(new GetGroupsEvent(MainApp.controller.authToken),this)));

        loop.start();
    }
    public void showGroups(){
        groups.getChildren().clear();
        groups.setMaxHeight(Region.USE_COMPUTED_SIZE);
        for (GroupChatOverview groupChat : response.groupChats) {
            groupChatOverview = groupChat;
            HBox hbox = new HBox(loadFXML.loadFXMl(MainApp.paths.groupItemPath));
            groups.getChildren().add(hbox);
            hbox.setOnMouseClicked(event -> showGroupChat(groupChat.groupId));
        }
    }
    public void showGroupChat(String groupId){
        groupChat = groupId;
        pane.getChildren().add(new AnchorPane(loadFXML.loadFXMl(MainApp.paths.groupChatPath)));
    }

    @Override
    public void visit(GetGroupsResponse getGroupsResponse) {
        this.response = getGroupsResponse;
        if (getGroupsResponse.groupChats == null || getGroupsResponse.groupChats.size() == 0){
            Platform.runLater(()->state.setText("No Groups Here!"));
        }
        else{
            Platform.runLater(this::showGroups);
        }
    }
}
