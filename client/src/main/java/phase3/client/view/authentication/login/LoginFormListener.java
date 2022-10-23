package phase3.client.view.authentication.login;


import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.shared.events.authentication.LoginFormEvent;
import phase3.client.listener.FormListener;
import phase3.client.view.LoadFXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import phase3.client.view.MainApp;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.authentication.LoginResponse;


public class LoginFormListener implements ResponseVisitor<LoginResponse> {
    public Stage mainStage ;
    private LoadFXML loadFXML = new LoadFXML();
    LoginController controller1;
    public void EventOccurred(Stage stage, LoginFormEvent loginFormEvent, LoginController controller) {

        NewEvent event = new NewEvent(loginFormEvent, this);
        MainApp.controller.addEvent(event);
        mainStage = stage;
        controller1 = controller;
    }

    @Override
    public void visit(LoginResponse loginResponse) {
        if (!loginResponse.authToken.equals("") ){
            MainApp.controller.authToken = loginResponse.authToken;
            Platform.runLater(()->mainStage.setScene(new Scene(loadFXML.loadFXMl(MainApp.paths.mainPagePath))));
        }
        else {
            Platform.runLater(()->controller1.setStatus( loginResponse.message));
        }
    }

}