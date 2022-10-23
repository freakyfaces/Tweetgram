package phase3.client.view.messaging.categories;

import javafx.application.Platform;
import phase3.client.config.CategoryStates;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import phase3.client.model.NewEvent;
import phase3.shared.events.messaging.CategoryActionEvent;
import phase3.shared.events.messaging.GetCategoriesEvent;
import phase3.shared.model.messaging.Category;
import phase3.client.view.LoadFXML;
import phase3.client.view.MainApp;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.messaging.GetCategoriesResponse;
import phase3.shared.util.Loop;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesOverviewController implements Initializable, ResponseVisitor<GetCategoriesResponse> {
    private CategoryStates config;
    Loop loop;

    {
        try {
            config = new CategoryStates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    CategoryListener listener;
    GetCategoriesResponse response ;
    public static Category category;
    LoadFXML loadFXML = new LoadFXML();
    @FXML
    private VBox categories;
    @FXML
    private Button create;
    @FXML
    private TextField categoryName;
    @FXML
    private TextField memberUsername;
    @FXML
    public Label state;
    @FXML
    private TextField categoryNameForMessaging;
    @FXML
    private TextField messageText;
    @FXML
    private Button send;
    @FXML
    private Pane pane;
    @FXML
    public void sendMessage() throws IOException {
        listener.StringEventOccurred(new CategoryActionEvent(MainApp.controller.authToken, "",
                categoryName.getText(), messageText.getText()));
    }
    @FXML
    public void createCategory(){
        listener.StringEventOccurred(new CategoryActionEvent(MainApp.controller.authToken, memberUsername.getText(),
                categoryName.getText(), ""));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listener = new CategoryListener();
        CategoryListener.controller = this;
        MainApp.controller.addEvent(new NewEvent(new GetCategoriesEvent(MainApp.controller.authToken),
                this));
        loop = new Loop(1,()->
                MainApp.controller.addEvent(new NewEvent(new GetCategoriesEvent(MainApp.controller.authToken),
                        this)));
        loop.start();
    }
    public void showCategories(){
        categories.getChildren().clear();
        categories.setMaxHeight(Region.USE_COMPUTED_SIZE);
        for (Category category1 : response.categories) {
            category = category1;
            HBox hbox = new HBox(loadFXML.loadFXMl(MainApp.paths.categoriesItemPath));
            categories.getChildren().add(hbox);
        }
    }

    @Override
    public void visit(GetCategoriesResponse getCategoriesResponse) {
        response = getCategoriesResponse;
        Platform.runLater(()->showCategories());
    }
}
