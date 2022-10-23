package phase3.client.view.setting.privacy;



import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.setting.privacy.ChangePasswordEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import phase3.shared.events.setting.privacy.PrivacySettingEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.setting.privacy.PrivacySettingResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PrivacyPageController implements Initializable, ResponseVisitor<PrivacySettingResponse> {
    static String Status = "";
    PrivacyPageFormListener privacyPageStringListener = new PrivacyPageFormListener();
    ChangePassFormListener changePassFormListener = new ChangePassFormListener();
    public PrivacySettingResponse PrivacyResponse ;
    @FXML
    private ChoiceBox<String> pageStatusChoiceBox;
    @FXML
    private ChoiceBox<String> lastSeenChoiceBox;
    @FXML
    private ChoiceBox<String> accStatusChoiceBox;
    @FXML
    private Button changePass;
    @FXML
    private PasswordField oldPass;
    @FXML
    private PasswordField newPass1;
    @FXML
    private PasswordField newPass2;
    @FXML
    private Label passwordStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PrivacySettingEvent event1 = new PrivacySettingEvent(lastSeenChoiceBox.getValue(),
                pageStatusChoiceBox.getValue(), accStatusChoiceBox.getValue(), MainApp.controller.authToken);

        MainApp.controller.addEvent(new NewEvent(event1, this));

        accStatusChoiceBox.setValue("Active");
        pageStatusChoiceBox.setOnAction(this::pageState);
        lastSeenChoiceBox.setOnAction(this::lastSeen);
        accStatusChoiceBox.setOnAction(this::isActive);
    }

    public void pageState(Event event) {
        try {
            PrivacySettingEvent event1 = new PrivacySettingEvent(lastSeenChoiceBox.getValue(),
                    pageStatusChoiceBox.getValue(), accStatusChoiceBox.getValue(), MainApp.controller.authToken);
            privacyPageStringListener.EventOccurred(event1, this);
            setChoiceBoxes(event1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void lastSeen(Event event){
        try {
            PrivacySettingEvent event1 = new PrivacySettingEvent(lastSeenChoiceBox.getValue(),
                    pageStatusChoiceBox.getValue(), accStatusChoiceBox.getValue(), MainApp.controller.authToken);
            privacyPageStringListener.EventOccurred(event1,  this);
            setChoiceBoxes(event1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void isActive(Event event){
        try {
            PrivacySettingEvent event1 = new PrivacySettingEvent(lastSeenChoiceBox.getValue(),
                    pageStatusChoiceBox.getValue(), accStatusChoiceBox.getValue(), MainApp.controller.authToken);
            privacyPageStringListener.EventOccurred(event1, this);
            setChoiceBoxes(event1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changePassword(){

        changePassFormListener.EventOccurred((Stage) changePass.getScene().getWindow(),
                new ChangePasswordEvent( oldPass.getText(), newPass1.getText(), newPass2.getText()
                        , MainApp.controller.authToken), this);

    }
    public void setPasswordStatus(String s){
        passwordStatus.setText(s);
    }

    public void setChoiceBoxes(PrivacySettingEvent response){
        lastSeenChoiceBox.setValue(response.lastSeen);
        pageStatusChoiceBox.setValue(response.pageState);
        if (response.isActive.equals("true")){
            accStatusChoiceBox.setValue("Active");}
        else if (response.isActive.equals("false")){
            accStatusChoiceBox.setValue("Not Active");
        }
        else{
            accStatusChoiceBox.setValue(response.isActive);
        }
    }

    @Override
    public void visit(PrivacySettingResponse privacySettingResponse) {
        Platform.runLater(()->lastSeenChoiceBox.setValue(privacySettingResponse.lastSeen));
        Platform.runLater(()->pageStatusChoiceBox.setValue(privacySettingResponse.pageState));
        if (privacySettingResponse.isActive.equals("true")){
            Platform.runLater(()->accStatusChoiceBox.setValue("Active"));
        }
        else if (privacySettingResponse.isActive.equals("false")){
            Platform.runLater(()->accStatusChoiceBox.setValue("Not Active"));
        }
        else{
            Platform.runLater(()->accStatusChoiceBox.setValue(privacySettingResponse.isActive));
        }
    }
}
