package phase3.client.view.messaging.categories;

import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.messaging.CategoryActionEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.messaging.CategoryActionResponse;

public class CategoryListener implements ResponseVisitor<CategoryActionResponse> {
    public static CategoriesOverviewController controller;
    public void StringEventOccurred(CategoryActionEvent event){
        MainApp.controller.addEvent(new NewEvent(event,this));
    }

    @Override
    public void visit(CategoryActionResponse categoryActionResponse) {
        Platform.runLater(()->controller.state.setText(categoryActionResponse.message));
    }
}
