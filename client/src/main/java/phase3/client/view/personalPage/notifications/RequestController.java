package phase3.client.view.personalPage.notifications;

import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.notifications.RequestEvent;
import phase3.shared.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestController implements Initializable {
    private RequestStringListener stringListener = new RequestStringListener();
    public static NotificationsPage page;

    private String username = "";
    @FXML
    private Button accept;
    @FXML
    private Button reject;
    @FXML
    private Button rejectNotif;
    @FXML
    private Label requestText;

    @FXML
    public void accept() throws IOException {
        RequestEvent event = new RequestEvent(MainApp.controller.authToken, username, "accept");
        stringListener.StringEventOccurred(event);
    }
    @FXML
    public void reject() throws IOException {
        RequestEvent event = new RequestEvent(MainApp.controller.authToken, username, "reject");
        stringListener.StringEventOccurred(event);
    }
    @FXML
    public void rejectAndNotify() throws IOException {
        RequestEvent event = new RequestEvent(MainApp.controller.authToken, username, "rejectAndNotify");
        stringListener.StringEventOccurred(event);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        page = NotificationsPageStringListener.page;
        username = NotificationsPage.username;
        requestText.setText(username+ " Has Requested To Follow You!");
    }
}
