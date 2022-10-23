package phase3.client.view.personalPage.notifications;


import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.notifications.NotificationsEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.NotificationsResponse;


import java.io.IOException;

public class NotificationsPageStringListener implements ResponseVisitor<NotificationsResponse> {


    public static NotificationsPage page;

    public void StringEventOccurred(NotificationsEvent event, NotificationsPage page1) throws IOException {
        page = page1;
        MainApp.controller.addEvent(new NewEvent(event, this));
    }

    @Override
    public void visit(NotificationsResponse notificationsResponse) {
        NotificationsPage.response = notificationsResponse;
    }
}
