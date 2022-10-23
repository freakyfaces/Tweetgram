package phase3.client.view.setting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class SettingPageController {
    SettingPageStringListener stringListener = new SettingPageStringListener();
    @FXML
    private Button Privacy;
    @FXML
    private Button LogOut;
    @FXML
    private Button DeleteAccount;
    @FXML
    private BorderPane borderPane;
    @FXML
    void logOut() throws IOException {
        stringListener.StringEventOccurred((Stage) LogOut.getScene().getWindow(), "LogOut");
    }
    @FXML
    void privacy() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PrivacyPage.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(new Pane(root));
    }
    @FXML
    void deleteAcc() throws IOException {
        stringListener.StringEventOccurred((Stage) LogOut.getScene().getWindow(), "deleteAcc");
    }



}
