package phase3.client.view.personalPage.notifications;


import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.notifications.RequestEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.NotificationsResponse;

import java.io.IOException;

public class RequestStringListener implements ResponseVisitor<NotificationsResponse> {



    public void StringEventOccurred(RequestEvent event) throws IOException {

        MainApp.controller.addEvent(new NewEvent(event, this));


    }

    @Override
    public void visit(NotificationsResponse notificationsResponse) {
        NotificationsPage.response = notificationsResponse;
        Platform.runLater(()->RequestController.page.requests());
    }

}
