package phase3.client.view.authentication.login;


import phase3.shared.events.authentication.LoginFormEvent;
import phase3.client.listener.FormListener;
import phase3.client.listener.StringListener;
import phase3.client.view.authentication.BackToStartListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    static String status = "";
    private LoginFormListener formListener = new LoginFormListener();
    @FXML
    private Label Status;
    @FXML
    private Button back;
    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    @FXML
    private Button Login;
    private StringListener stringListener = new BackToStartListener();

    @FXML
    void LoginOccurred(ActionEvent event){
        LoginFormEvent loginFormEvent = new LoginFormEvent(Username.getText(), Password.getText());
        formListener.EventOccurred((Stage) Login.getScene().getWindow(), loginFormEvent, this);
        Status.setText(status);

    }
    @FXML
    void back(ActionEvent event){
        try {
            stringListener.StringEventOccurred((Stage) back.getScene().getWindow(), "back");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setStatus(String s){
        Status.setText(s);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}