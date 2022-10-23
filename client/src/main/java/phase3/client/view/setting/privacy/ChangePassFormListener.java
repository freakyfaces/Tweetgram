package phase3.client.view.setting.privacy;

import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.setting.privacy.ChangePasswordEvent;
import javafx.stage.Stage;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.setting.privacy.ChangePasswordResponse;

public class ChangePassFormListener implements ResponseVisitor<ChangePasswordResponse> {
    public PrivacyPageController controller1;

    public void EventOccurred(Stage stage, ChangePasswordEvent formEvent, PrivacyPageController controller) {

        MainApp.controller.addEvent(new NewEvent(formEvent, this));
        controller1 = controller;

    }

    @Override
    public void visit(ChangePasswordResponse changePasswordResponse) {
        Platform.runLater(()->controller1.setPasswordStatus(changePasswordResponse.message));
    }

}
