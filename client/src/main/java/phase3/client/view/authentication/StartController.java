package phase3.client.view.authentication;

import phase3.client.listener.StringListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    private StringListener stringListener = new StartStringListener();

    @FXML
    private Button SignUp;

    @FXML
    private Button Login;

    @FXML
    void GoToLogin() throws IOException {
        stringListener.StringEventOccurred((Stage) Login.getScene().getWindow(), "login");
    }

    @FXML
    void GoToSignUp() throws IOException {
        stringListener.StringEventOccurred((Stage) Login.getScene().getWindow(), "signup");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
