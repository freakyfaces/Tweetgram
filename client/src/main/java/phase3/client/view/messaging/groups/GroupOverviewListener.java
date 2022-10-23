package phase3.client.view.messaging.groups;

import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.messaging.CreateGroupEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.messaging.CreateGroupResponse;

public class GroupOverviewListener implements ResponseVisitor<CreateGroupResponse> {
    public GroupOverviewController controller;
    public void EventOccurred(CreateGroupEvent event, GroupOverviewController controller){
        MainApp.controller.addEvent(new NewEvent(event,this));
        this.controller = controller;
    }
    @Override
    public void visit(CreateGroupResponse createGroupResponse) {
        Platform.runLater(()->controller.state.setText(createGroupResponse.message));
    }

}
