package phase3.client.view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public static String[] pages = {"", ""};
    MainPageListener stringListener = new MainPageListener();
    @FXML
    private Button previous;
    @FXML
    public void previous() throws IOException {
        stringListener.StringEventOccurred(MainPane, pages[0]);
    }
    @FXML
    void setting() throws IOException {
        stringListener.StringEventOccurred(MainPane, "setting");
    }
    @FXML
    void explorer() throws IOException {
        stringListener.StringEventOccurred(MainPane, "explorer");
    }
    @FXML
    void personalPage() throws IOException {
        stringListener.StringEventOccurred(MainPane, "personalPage");
    }
    @FXML
    void timeLine() throws IOException {
        stringListener.StringEventOccurred(MainPane, "timeline");
    }
    @FXML
    void messaging() throws IOException {
        stringListener.StringEventOccurred(MainPane, "messaging");
    }
    @FXML
    public BorderPane MainPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static void updatePages(String newPage){
        pages[0] = pages[1];
        pages[1] = newPage;
    }
}
