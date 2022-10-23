package phase3.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class LoadFXML {
    public Parent loadFXMl(String path){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
    public Parent loadFXMl(String path, Object controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        fxmlLoader.setController(controller);
        Parent root = null;
        try {
            root = fxmlLoader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}
