package phase3.client.view.authentication;


import phase3.client.listener.StringListener;
import phase3.client.view.LoadFXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import phase3.client.view.MainApp;

import java.io.IOException;

public class BackToStartListener implements StringListener<Stage> {
    private LoadFXML loadFXML = new LoadFXML();
    @Override
    public void StringEventOccurred(Stage window, String string) throws IOException {
        Scene scene = new Scene(loadFXML.loadFXMl(MainApp.paths.startPagePath));
        window.setScene(scene);
    }
}
