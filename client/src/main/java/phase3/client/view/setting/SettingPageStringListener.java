package phase3.client.view.setting;


import phase3.client.listener.StringListener;
import phase3.client.model.NewEvent;
import phase3.client.view.LoadFXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import phase3.client.view.MainApp;
import phase3.shared.events.setting.privacy.LogOutEvent;

import java.io.IOException;

public class SettingPageStringListener implements StringListener<Stage> {
    private LoadFXML loadFXML = new LoadFXML();
    @Override
    public void StringEventOccurred(Stage window, String string) throws IOException {
        if (string.equals("LogOut")) {
            MainApp.controller.addEvent(new NewEvent(new LogOutEvent(MainApp.controller.authToken),null));
            window.setScene(new Scene(loadFXML.loadFXMl(MainApp.paths.startPagePath)));
        }
        else if (string.equals("privacy")){
            window.setScene(new Scene(loadFXML.loadFXMl(MainApp.paths.privacyPagePath)));
        }
        else if (string.equals("deleteAcc")){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Are you sure you want to delete your account?"
            , ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.YES)){
               // DeleteAccountController.deleteAccount(AuthController.user);
                window.setScene(new Scene(loadFXML.loadFXMl(MainApp.paths.startPagePath)));
            }
        }
    }
}
