package phase3.client.view.explorer.search;

import javafx.application.Platform;
import phase3.client.config.explorer.ProfileConfig;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.explorer.ProfilePageActionsEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.explorer.ProfileActionResponse;
import java.io.IOException;

public class ProfileStringListener implements ResponseVisitor<ProfileActionResponse> {
    ProfileConfig config;
    {
        try {
            config = new ProfileConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ProfileController controller1;
    public void StringEventOccurred(ProfilePageActionsEvent event , ProfileController controller) throws IOException {
        this.controller1 = controller;
        MainApp.controller.addEvent(new NewEvent(event,this));

    }

    @Override
    public void visit(ProfileActionResponse profileActionResponse) {
        if (controller1 != null){
            Platform.runLater(()->controller1.setAlert(profileActionResponse.message));
            if (profileActionResponse.buttonText.equals("Block") || profileActionResponse.buttonText.equals("Unblock")){
                Platform.runLater(()->controller1.block.setText(profileActionResponse.buttonText));
            }
            else if (!profileActionResponse.buttonText.equals("Report")){
                Platform.runLater(()->controller1.follow.setText(profileActionResponse.buttonText));
            }
        }
    }
}
