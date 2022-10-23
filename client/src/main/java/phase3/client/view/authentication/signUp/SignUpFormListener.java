package phase3.client.view.authentication.signUp;

import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.shared.events.authentication.SignUpFormEvent;
import phase3.client.listener.FormListener;
import phase3.shared.response.ResponseVisitor;
import phase3.client.view.LoadFXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import phase3.client.view.MainApp;
import phase3.shared.response.authentication.SignUpResponse;


public class SignUpFormListener implements  ResponseVisitor<SignUpResponse> {
    static LoadFXML loadFXML = new LoadFXML();
    static Stage mainStage;
    SignUpController controller;

    public void EventOccurred(Stage stage, SignUpFormEvent formEvent, SignUpController controller1) {
        NewEvent event = new NewEvent(formEvent, this);
        MainApp.controller.addEvent(event);
        mainStage = stage;
        controller = controller1;

    }

    @Override
    public void visit(SignUpResponse message) {
        if (!message.authToken.equals("") ){
            MainApp.controller.authToken = message.authToken;
            Platform.runLater(()->mainStage.setScene(new Scene(loadFXML.loadFXMl(MainApp.paths.mainPagePath))));
        }
        else {
            Platform.runLater(()->controller.setStatus(message.message));
        }

    }
}
