package phase3.client.view.explorer;


import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.client.view.explorer.search.ProfileController;
import phase3.shared.events.explorer.GetProfileEvent;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import phase3.client.view.LoadFXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.explorer.GetProfileResponse;
import java.io.IOException;
import java.util.LinkedList;

public class ExplorerStringListener implements ResponseVisitor<GetProfileResponse> {
    private final LoadFXML loadFXML = new LoadFXML();
    public static User user;
    public static LinkedList<Tweet> tweets;
    public BorderPane borderPane1;
    public ExplorerPageController controller1;
    public void StringEventOccurred(BorderPane borderPane, String string, ExplorerPageController controller) throws IOException {

        this.borderPane1 = borderPane;
        this.controller1 = controller;

        if (string.equals("explorer")){
            borderPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.explorerPagePath)));
        }

        else{
            NewEvent event = new NewEvent(new GetProfileEvent(MainApp.controller.authToken, string), this);
            MainApp.controller.addEvent(event);
        }

    }

    @Override
    public void visit(GetProfileResponse getProfileResponse) {
        if (getProfileResponse.message != null){
            Platform.runLater(()->controller1.setStatus(getProfileResponse.message));
        }
        else{
            Platform.runLater(()->controller1.setStatus(""));
            ProfileController.getProfileResponse = getProfileResponse;
            Platform.runLater(()->borderPane1.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.profilePagePath))));
        }
    }

}
