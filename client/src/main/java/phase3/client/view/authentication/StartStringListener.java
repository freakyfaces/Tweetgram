package phase3.client.view.authentication;

import phase3.client.listener.StringListener;
import phase3.client.view.LoadFXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import phase3.client.view.MainApp;

import java.io.IOException;

public class StartStringListener implements StringListener<Stage> {
    static LoadFXML loadFXML = new LoadFXML();
    @Override
    public void StringEventOccurred(Stage stage, String string) throws IOException {
        if (string.equals("login")){
            stage.setScene(new Scene(loadFXML.loadFXMl(MainApp.paths.loginPagePath)));
        }
        if (string.equals("signup")){
            stage.setScene(new Scene(loadFXML.loadFXMl(MainApp.paths.signUpPagePath)));
        }
    }

}
