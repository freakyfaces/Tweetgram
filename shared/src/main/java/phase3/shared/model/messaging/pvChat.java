package phase3.shared.model.messaging;



import phase3.shared.model.Tweet;
import phase3.shared.model.User;

import java.io.IOException;
import java.util.LinkedList;

public class pvChat {
    public String id1;
    public String id2;
    public LinkedList<Message> messages;
    public int unreadid1;
    public int unreadid2;
    public String id;
    public pvChat(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
        this.id = id1+id2;
        this.unreadid1 = 0;
        this.unreadid2 = 0;
        this.messages = new LinkedList<>();
        User user = User.getUser(id1);
        if (user.Id.equals(id1)){
            user.pvs.add(this.id);
        }
        user = User.getUser(id2);
        if (user.Id.equals(id2)){
            user.pvs.add(this.id);
        }
    }
    public void sendMsg(String text, String giverId) throws IOException {
        String reciverId = "";
        if (giverId.equals(this.id1)){
            reciverId = this.id2;
        }
        else{
            reciverId = this.id1;
        }
        Message msg = new Message(reciverId, giverId, text);
        this.messages.add(msg);
        if (reciverId.equals(this.id1)){
            this.unreadid1++;
        }
        else{
            this.unreadid2++;
        }
    }
    public void sendMsg(Tweet text, String giverId) throws IOException {
        String reciverId = "";
        if (giverId.equals(id1)){
            reciverId = id2;
        }
        else{
            reciverId = id1;
        }
        Message msg = new Message(reciverId, giverId, text);
        this.messages.add(msg);
        if (reciverId.equals(this.id1)){
            this.unreadid1++;
        }
        else{
            this.unreadid2++;
        }
    }
    public Message getMsg(String id){
        for (Message message : this.messages) {
            if (message.id.equals(id)){
                return message;
            }
        }
        return messages.get(0);
    }
}

