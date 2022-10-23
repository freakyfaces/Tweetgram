package phase3.shared.model.messaging;

import phase3.shared.model.Tweet;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class SavedMessage{
    public LinkedList<Message> messages;
    String id;
    public SavedMessage (String id){
        this.id = id;
        this.messages = new LinkedList<>();
    }
    public void saveMessage(Tweet tweet) throws FileNotFoundException {
        Message message = new Message(this.id, this.id, tweet.out());
        messages.add(message);
    }
    public void saveMessage(String text) throws FileNotFoundException {
        Message message = new Message(this.id, this.id, text);
        messages.add(message);
    }
}
