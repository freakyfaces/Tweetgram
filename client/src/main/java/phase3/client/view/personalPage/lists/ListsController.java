package phase3.client.view.personalPage.lists;


import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.LoadFXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.ListsEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.ListsResponse;
import phase3.shared.util.Loop;

import java.net.URL;
import java.util.ResourceBundle;

public class ListsController implements Initializable, ResponseVisitor<ListsResponse> {
    LoadFXML loadFXML = new LoadFXML();
    public static String username = "";
    public static String kind = "";
    public static byte[] profile;
    public static ListsResponse response;
    @FXML
    private VBox followers;
    @FXML
    private VBox followings;
    @FXML
    private VBox blackList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Loop loop = new Loop(1, ()-> MainApp.controller.addEvent(
                new NewEvent(new ListsEvent(MainApp.controller.authToken),this)));

        loop.start();
    }

    @Override
    public void visit(ListsResponse listsResponse) {
        response = listsResponse;
        Platform.runLater(()->followings.getChildren().clear());
        Platform.runLater(()->followers.getChildren().clear());
        Platform.runLater(()->blackList.getChildren().clear());
        for (int i = 0; i < listsResponse.followers.size(); i++) {
            username = listsResponse.followers.get(i);
            profile = listsResponse.followersPic.get(i);
            kind = "follower";
            Platform.runLater(()->followers.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.listsProfilePath))));
        }
        for (int i = 0; i < listsResponse.followings.size(); i++) {
            System.out.println(1);
            username = listsResponse.followings.get(i);
            profile = listsResponse.followingsPic.get(i);
            kind = "following";
            Platform.runLater(()->followings.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.listsProfilePath))));
        }
        for (int i = 0; i < listsResponse.blackList.size(); i++) {
            username = listsResponse.blackList.get(i);
            profile = listsResponse.blackListPic.get(i);
            kind = "blacklist";
            Platform.runLater(()->blackList.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.listsProfilePath))));
        }
    }
}
