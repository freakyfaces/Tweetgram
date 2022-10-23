package phase3.client.view;

import phase3.client.listener.StringListener;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class MainPageListener implements StringListener<BorderPane> {
    private LoadFXML loadFXML = new LoadFXML();
    @Override
    public void StringEventOccurred(BorderPane MainPane, String string) throws IOException {
        MainPageController.updatePages(string);
        if (string.equals("setting")){
            MainPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.settingPagePath)));
        }
        else if (string.equals("explorer")){
            MainPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.explorerPagePath)));
        }
        else if (string.equals("personalPage")){
            MainPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.personalPagePath)));
        }
        else if (string.equals("messaging")){
            MainPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.messagingPagePath)));
        }
        else if (string.equals("timeline")){
            MainPane.setCenter(new Pane(loadFXML.loadFXMl(MainApp.paths.timelinePagePath)));
        }

    }
}
