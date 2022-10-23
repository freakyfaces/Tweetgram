package phase3.client.view.messaging;


import javafx.application.Platform;
import javafx.scene.layout.*;
import phase3.client.model.NewEvent;
import phase3.shared.events.messaging.GetPvsEvent;
import phase3.shared.model.messaging.GroupChat;
import phase3.shared.model.messaging.PvChatOverview;
import phase3.shared.model.messaging.pvChat;
import phase3.client.view.LoadFXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import phase3.client.view.MainApp;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.messaging.GetPvsResponse;
import phase3.shared.util.Loop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessagingPageController implements Initializable, ResponseVisitor<GetPvsResponse> {
    public static GetPvsResponse response;
    public static GroupChat groupChat;
    MessagingPageStringListener listener = new MessagingPageStringListener();
    public static PvChatOverview pvChat;
    public static String pvChatId;
    Loop loop;
    private LoadFXML loadFXML = new LoadFXML();
    @FXML
    private Button search;
    @FXML
    private Button pvs;
    @FXML
    private Button groups;
    @FXML
    private Button Saved;
    @FXML
    public Label state;
    @FXML
    private BorderPane main;
    @FXML
    private TextField searchText;
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label status;
    @FXML
    private Button categories;
    @FXML
    public void Saved(){
        loop.stop();
        status.setText("");
        vbox.getChildren().clear();
        main.getChildren().clear();
        main.setLeft(new AnchorPane(loadFXML.loadFXMl(MainApp.paths.savedMessagePagePath)));
    }
    @FXML
    public void groups(){
        loop.stop();
        main.getChildren().clear();
        main.setCenter(loadFXML.loadFXMl(MainApp.paths.groupOverviewPath));
    }
    @FXML
    public void pvs() throws FileNotFoundException {
        if (response == null || response.pvChats.size() == 0){
            main.getChildren().clear();
            main.setCenter(status);
            status.setText("No PVs Here...");
        }
        else {
            status.setText("");
            main.getChildren().clear();
            main.setCenter(scrollPane);
            vbox.getChildren().clear();
            for (PvChatOverview pvChat1: response.pvChats) {
                pvChat = pvChat1;
                HBox hBox = new HBox(loadFXML.loadFXMl(MainApp.paths.contactPath));
                vbox.getChildren().add(hBox);
                hBox.setOnMouseClicked(e -> showPvChat(pvChat1.pvId));
            }
        }
    }
    @FXML
    public void search() throws IOException {
        listener.StringEventOccurred(this, searchText.getText());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loop = new Loop(1,()->MainApp.controller.addEvent(
                new NewEvent(new GetPvsEvent(MainApp.controller.authToken),this)));

        loop.start();
    }
    public void showPvChat(String pvId){
        loop.stop();
        main.getChildren().clear();
        pvChatId = pvId;
        main.setLeft(new AnchorPane(loadFXML.loadFXMl(MainApp.paths.chatPagePath)));
    }
    @FXML
    public void categories(){
        loop.stop();
        main.getChildren().clear();
        main.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.categoriesOverviewPath)));
    }

    @Override
    public void visit(GetPvsResponse getPvsResponse) {
        response = getPvsResponse;
            Platform.runLater(() -> {
                try {
                    pvs();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
    }
}
