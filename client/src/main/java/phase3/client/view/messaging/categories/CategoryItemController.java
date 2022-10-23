package phase3.client.view.messaging.categories;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import phase3.shared.model.messaging.Category;
import phase3.shared.model.User;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryItemController implements Initializable {
    @FXML
    private Label members;
    @FXML
    private Label name;
    @FXML
    public Pane pane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Category category = CategoriesOverviewController.category;
        StringBuilder shown = new StringBuilder();
        for (String user : category.people) {
            shown.append(User.id2username(user)).append(" ");
        }
        name.setText(category.name);
        members.setText(shown.toString());
    }
}
