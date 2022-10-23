package phase3.client.view.personalPage.editProfile;


import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.EditProfileFormEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.EditProfileResponse;

public class EditProfileFormListener implements ResponseVisitor<EditProfileResponse> {

    public EditProfilePageController pageController;
    public void EventOccurred(EditProfilePageController controller, EditProfileFormEvent formEvent) {
        MainApp.controller.addEvent(new NewEvent(formEvent, this));
        pageController = controller;
    }

    @Override
    public void visit(EditProfileResponse editProfileResponse) {
        Platform.runLater(()->pageController.setStatus(editProfileResponse.message));
    }
}
