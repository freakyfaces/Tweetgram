package phase3.shared.model.messaging;

import java.util.LinkedList;

public class GroupChatOverview {
    public String shown;
    public String name;
    public String unseen;
    public String groupId;
    public LinkedList<String> users;
    public GroupChatOverview(String shown, String name, String unseen, String groupId, LinkedList<String> users) {
        this.shown = shown;
        this.name = name;
        this.unseen = unseen;
        this.groupId = groupId;
        this.users = users;
    }
}
