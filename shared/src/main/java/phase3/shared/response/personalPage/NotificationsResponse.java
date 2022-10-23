package phase3.shared.response.personalPage;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

import java.util.LinkedList;

public class NotificationsResponse extends Response {

    public LinkedList<String> notifications;
    public LinkedList<String> requests;

    public NotificationsResponse(LinkedList<String> notifications, LinkedList<String> requests) {
        this.notifications = notifications;
        this.requests = requests;
    }


    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
