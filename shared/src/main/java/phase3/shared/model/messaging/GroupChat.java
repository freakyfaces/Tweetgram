package phase3.shared.model.messaging;

import phase3.shared.model.User;

import java.util.LinkedList;

public class GroupChat {
    public static LinkedList<GroupChat> groupChats = new LinkedList<>();
    public LinkedList<String> users;
    public LinkedList<Message> messages;
    public LinkedList<Integer> unseen;
    public int id;
    public String name;
    public GroupChat(String name) {
        this.messages = new LinkedList<>();
        this.users = new LinkedList<>();
        this.id = groupChats.size();
        this.unseen = new LinkedList<>();
        this.name = name;
        groupChats.add(this);
    }

    public LinkedList<String> getUsers() {
        return users;
    }

    public void addUsers(String user) {
        this.users.add(user);
        User.getUser(user).groups.add(this.id);
        this.unseen.add(messages.size());

    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
    public void doUnseen(String id){
        int i = this.getUsers().indexOf(id);
        for (int i1 = 0; i1 < this.unseen.size(); i1++) {
            if (i1 != i){
                this.unseen.set(i1, this.unseen.get(i1)+1);
            }
        }
    }
    public String getUnseen (String id){
        int i = this.users.indexOf(id);
        return unseen.get(i)+"";
    }
    public void removeUnseen(String id){
        this.unseen.set(this.getUsers().indexOf(id), 0);
    }
}
