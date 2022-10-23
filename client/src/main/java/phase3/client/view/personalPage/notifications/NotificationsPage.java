package phase3.client.view.personalPage.notifications;

import phase3.shared.events.personalPage.notifications.NotificationsEvent;
import phase3.client.view.LoadFXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import phase3.client.view.MainApp;
import phase3.shared.response.personalPage.NotificationsResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotificationsPage implements Initializable {
    public static String username;
    private LoadFXML loadFXML = new LoadFXML();

    NotificationsPageStringListener stringListener = new NotificationsPageStringListener();
    public static NotificationsResponse response;
    @FXML
    private Label state;
    @FXML
    private Button requests;
    @FXML
    private Button systemNotifications;
    @FXML
    private VBox vbox;
    @FXML
    public void requests() {

        if (response.requests.size() > 0) {
            vbox.getChildren().clear();
            state.setText("");
            showRequests();
        } else {
            vbox.getChildren().clear();
            state.setText("There's Nothing To Show Here...");
        }

    }
    @FXML
    public void systemNotifications(){

        if (response.notifications.size()>0){
            state.setText("");
            vbox.getChildren().clear();
            for (String notification : response.notifications) {
                String[] s = notification.split("\\s+");
                Label label = new Label();
                if (s[1].equals("follow")){
                    label.setText(s[0]+" Followed You!");
                }
                else if (s[1].equals("accept")){
                    label.setText(s[0]+" Accepted Your Request!");
                }
                else if (s[1].equals("reject")){
                    label.setText(s[0]+" Rejected Your Request!");
                }
                else {
                    label.setText(s[0]+" Unfollowed You!");
                }
                label.setFont(new Font("Arial", 20));
                vbox.getChildren().add(label);
            }
        }
        else{
            vbox.getChildren().clear();
            state.setText("There's Nothing To Show Here...");
        }
    }
    public void showRequests(){

        vbox.getChildren().clear();
        for (String request : response.requests) {
            username = request;
            vbox.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.requestPath)));
        }


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequestController.page = this;
        try {
            stringListener.StringEventOccurred(new NotificationsEvent(MainApp.controller.authToken), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
