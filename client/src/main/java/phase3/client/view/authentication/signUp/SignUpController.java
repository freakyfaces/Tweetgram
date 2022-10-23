package phase3.client.view.authentication.signUp;

import javafx.application.Platform;
import phase3.shared.events.authentication.SignUpFormEvent;
import phase3.client.listener.FormListener;
import phase3.client.listener.StringListener;
import phase3.client.view.authentication.BackToStartListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import phase3.shared.util.Loop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    private SignUpFormListener formListener = new SignUpFormListener();
    private StringListener stringListener = new BackToStartListener();
    static String status ="" ;
    @FXML
    private Button signup;
    @FXML
    private Button back;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField bio;
    @FXML
    private TextField number;
    @FXML
    private DatePicker date;
    @FXML
    private Label Status;
    @FXML
    void signupOccurred(ActionEvent event){
        String date1 = "";
        if (date.getValue() != null){
            date1 = date.getValue().toString();
        }
        SignUpFormEvent signUpFormEvent = new SignUpFormEvent( username.getText(), password.getText()
        , email.getText(), name.getText(), date1, number.getText(), bio.getText());
        formListener.EventOccurred((Stage) signup.getScene().getWindow(), signUpFormEvent, this);

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
