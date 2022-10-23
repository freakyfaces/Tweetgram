package phase3.client.view.setting.privacy;



import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.setting.privacy.PrivacySettingEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.setting.privacy.PrivacySettingResponse;

import java.io.IOException;

public class PrivacyPageFormListener implements ResponseVisitor<PrivacySettingResponse> {

    PrivacyPageController controller1;
    public void EventOccurred(PrivacySettingEvent event, PrivacyPageController controller) throws IOException {
        controller1 = controller;
        MainApp.controller.addEvent(new NewEvent(event, this));

    }

    @Override
    public void visit(PrivacySettingResponse privacySettingResponse) {
        controller1.PrivacyResponse = privacySettingResponse;

    }
}
