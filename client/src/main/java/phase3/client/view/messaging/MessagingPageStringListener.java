package phase3.client.view.messaging;

import javafx.application.Platform;
import phase3.client.listener.StringListener;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.client.view.messaging.pv.ChatPageController;
import phase3.shared.events.messaging.SearchForPvEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.messaging.SearchPvResponse;

import java.io.IOException;

public class MessagingPageStringListener implements StringListener<MessagingPageController>, ResponseVisitor<SearchPvResponse> {
    MessagingPageController controller ;
    @Override
    public void StringEventOccurred(MessagingPageController controller1, String username) throws IOException {
        MainApp.controller.addEvent(new NewEvent(new SearchForPvEvent(MainApp.controller.authToken, username)
                ,this));
        this.controller = controller1;
    }

    @Override
    public void visit(SearchPvResponse searchPvResponse) {
        if (searchPvResponse.Message.equals("")){
            Platform.runLater(()->controller.showPvChat(searchPvResponse.pvChat.id));
        }
        else{
            Platform.runLater(()->controller.state.setText(searchPvResponse.Message));
        }
    }
}
