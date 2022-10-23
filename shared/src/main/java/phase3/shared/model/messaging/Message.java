package phase3.shared.model.messaging;


import phase3.shared.model.Tweet;
import phase3.shared.model.User;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Message {
    public String reciever;
    public String text;
    public String giver;
    public String dateTime;
    boolean seen ;
    public String id;
    public static DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dformatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public Message(String rId, String gId, String text) throws FileNotFoundException {
        this.reciever = rId;
        this.giver = gId;
        this.text = text;
        this.dateTime = LocalDateTime.now().format(dtformatter);
        this.seen = false;

        if (this.id == null){
            this.id = "0";
        }
        if (this.reciever.equals(this.giver)){
            this.id= User.getUser(this.giver).savedMessage.messages.size()+"";
        }
        if (this.reciever.length() < 2){
            this.id= (GroupChat.groupChats.get(Integer.parseInt(this.reciever)).getMessages().size()+1)+"";
        }
    }
    public Message(String rId, String gId, Tweet message) throws FileNotFoundException {
        this.reciever = rId;
        this.giver = gId;
        this.text = message.out();
        this.dateTime = LocalDateTime.now().format(dtformatter);
        this.seen = false;
        for (String pv : User.getUser(rId).pvs) {
            if (pv.equals(rId+gId) || pv.equals(gId+rId)){
              //  this.id = Load.loadPvChat(pv).messages.size()+"";
            }
        }
        if (this.id.equals("")){
            this.id = "0";
        }
        if (this.reciever.equals(this.giver)){
            this.id= User.getUser(this.giver).savedMessage.messages.size()+"";
        }
        if (this.reciever.length() < 2){
            this.id=(GroupChat.groupChats.get(Integer.parseInt(this.reciever)).getMessages().size()+1)+"";
        }
    }
    void edit(String text){
        this.text = text;
    }
    @Override
    public String toString(){
        return this.text;
    }
    public void setId(String id){
        this.id = id;
    }

}
