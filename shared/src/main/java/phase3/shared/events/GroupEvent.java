package phase3.shared.events;

import java.util.EventObject;

public class GroupEvent extends EventObject {
    private String groupName;
    private String member;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public GroupEvent(Object source, String groupName, String member) {
        super(source);
        this.groupName = groupName;
        this.member = member;
    }
}
