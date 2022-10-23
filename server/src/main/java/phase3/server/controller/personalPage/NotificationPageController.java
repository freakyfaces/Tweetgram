package phase3.server.controller.personalPage;

import phase3.shared.events.personalPage.notifications.RequestEvent;
import phase3.shared.model.User;
import phase3.shared.response.personalPage.NotificationsResponse;
import java.util.LinkedList;

public class NotificationPageController {

    public static NotificationsResponse notifications(String authToken){
        User user = User.getUserByAuthToken(authToken);
        LinkedList<String> notifications = new LinkedList<>();
        for (int i = 0; i < user.notifs.size(); i++) {
            String[] s = user.notifs.get(i).split("\\s+");
            notifications.add(User.id2username(s[0]) + " " + s[1]);
        }
        LinkedList<String> requests = new LinkedList<>();
        for (int i = 0; i < user.requests.size(); i++) {
            requests.add(User.id2username(user.requests.get(i)));
        }
        user.notifs.clear();
        return new NotificationsResponse(notifications, requests);

    }

    public static NotificationsResponse requests(RequestEvent event){
        User user = User.getUserByAuthToken(event.authToken);
        if (event.status.equals("accept")){
            if (!user.followers.contains(User.username2id(event.username))){
                user.requests.remove(User.username2id(event.username));
                User.getUser(User.username2id(event.username)).notifs.add((user.Id)+" accept");
                user.followers.add(User.username2id(event.username));
                User.getUser(User.username2id(event.username)).following.add(user.Id);
            }
        }
        else if (event.status.equals("rejectAndNotify")){
            user.requests.remove(User.username2id(event.username));
            User.getUser(User.username2id(event.username)).notifs.add((user.Id)+" reject");
        }
        else if (event.status.equals("reject")){
            user.requests.remove(User.username2id(event.username));
        }
        return notifications(event.authToken);
    }

}
