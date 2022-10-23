package phase3.server.dataBase;


import com.google.gson.reflect.TypeToken;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import phase3.shared.model.messaging.GroupChat;
import phase3.shared.model.messaging.Message;
import phase3.shared.model.messaging.SavedMessage;
import phase3.shared.model.messaging.pvChat;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;

public class Load {
    public static org.apache.log4j.Logger logger= Logger.getLogger(Load.class);


    public static void readData(){
        try {
            Type listType = new TypeToken<LinkedList<User>>(){}.getType();
            User.userList = Save.gson.fromJson(new FileReader("server/data/Users.json"),listType);
            listType = new TypeToken<LinkedList<GroupChat>>(){}.getType();
            GroupChat.groupChats = Save.gson.fromJson(new FileReader("server/data/Groups.json"),listType);
            User.LastId = User.userList.size();
            for (User user : User.userList) {
                readList(user, "PVs");
                readList(user, "tweet");
                try{
                    user.savedMessage = Save.gson.fromJson(new FileReader("server/data/"+user.Id +"/savedMessage"+user.Id+".json")
                            , SavedMessage.class);
                }
                catch (FileNotFoundException ee){
                    user.savedMessage = new SavedMessage(user.Id);
                }
            }
        }
        catch (FileNotFoundException e){
            logger.error("data files not found");
            User.userList = new LinkedList<>();
        }
    }
    static void readList(User user, String name){
        Type pvsType = new TypeToken<LinkedList<pvChat>>(){}.getType();
        Type tweetsType = new TypeToken<LinkedList<Tweet>>(){}.getType();
        try {
            if (name.equals("tweet")){
                user.Tweets = Save.gson.fromJson(new FileReader("server/data/"+user.Id +"/Tweets"+user.Id+".json"),tweetsType);
            }
            else{
                user.pvs = Save.gson.fromJson(new FileReader("server/data/"+user.Id +"/PVs"+user.Id+".json"),pvsType);
            }
        }
        catch (FileNotFoundException e){
            logger.error("data files not found");
            if (name.equals("tweet")){
                user.Tweets = new LinkedList<Tweet>();
            }
        }
    }

    public static pvChat loadPvChat(String id) throws FileNotFoundException {
        File file = new File("server/data/pvChats/"+id+"/"+id+".json");

        if (file.exists()){
            logger.info("PvChat Loaded");
            return Save.gson.fromJson(new FileReader("server/data/pvChats/"+id+"/"+id+".json"), pvChat.class);
        }
        file = new File("server/data/pvChats/"+"00"+"/"+"00.json");
        if (file.exists()){
            return Save.gson.fromJson(new FileReader("server/data/pvChats/00/00.json"), pvChat.class);
        }
        return new pvChat("0", "0");

    }


    public static byte[] picToByte(String id) throws IOException {
        File fNew = new File ("server/data/"+ id+"/ProfilePicture.jpg");
        if (fNew.exists()){
            BufferedImage originalImage= ImageIO.read(fNew);
            ByteArrayOutputStream bArray=new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", bArray );
            return bArray.toByteArray();
        }
        return null;
    }

    public static byte[] loadTweetPic(String tweetId, String userId) throws IOException{
        File fNew = new File ("server/data/" + userId + "/TweetsPictures/" +
                 tweetId + ".jpg");
        if (fNew.exists()){
            BufferedImage originalImage= ImageIO.read(fNew);
            ByteArrayOutputStream bArray=new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", bArray );
            return bArray.toByteArray();
        }
        return null;
    }

    public static LinkedList<byte[]> loadSavedMessagePics(String authToken) throws IOException {
        User user = User.getUserByAuthToken(authToken);
        LinkedList<byte[]> pics = new LinkedList<>();
        for (Message message : user.savedMessage.messages) {
            File fNew = new File ("server/data/"+user.Id+"/SavedMessagesPics/"
                    +(message.id)+".jpg");
            if (fNew.exists()){
                BufferedImage originalImage= ImageIO.read(fNew);
                ByteArrayOutputStream bArray=new ByteArrayOutputStream();
                ImageIO.write(originalImage, "jpg", bArray );
                pics.add(bArray.toByteArray());
            }
            else{
                pics.add(null);
            }
        }
        return pics;
    }
    public static LinkedList<byte[]> loadPvChatPics(String authToken, String pvId) throws IOException {
        User user = User.getUserByAuthToken(authToken);
        LinkedList<byte[]> pics = new LinkedList<>();
        for (Message message : loadPvChat(pvId).messages) {
            File fNew = new File ("server/data/pvChats/"+pvId+"/"
                    +(Integer.parseInt(message.id))+".jpg");
            if (fNew.exists()){
                BufferedImage originalImage= ImageIO.read(fNew);
                ByteArrayOutputStream bArray=new ByteArrayOutputStream();
                ImageIO.write(originalImage, "jpg", bArray );
                pics.add(bArray.toByteArray());
            }
            else{
                pics.add(null);
            }
        }
        return pics;
    }
    public static LinkedList<byte[]> loadGroupPics(String authToken, String groupId) throws IOException {
        User user = User.getUserByAuthToken(authToken);
        LinkedList<byte[]> pics = new LinkedList<>();
        for (Message message : GroupChat.groupChats.get(Integer.parseInt(groupId)).messages) {
            File fNew = new File ("server/data/Groups/"+groupId+"/"
                    +(Integer.parseInt(message.id))+".jpg");
            if (fNew.exists()){
                BufferedImage originalImage= ImageIO.read(fNew);
                ByteArrayOutputStream bArray=new ByteArrayOutputStream();
                ImageIO.write(originalImage, "jpg", bArray );
                pics.add(bArray.toByteArray());
            }
            else{
                pics.add(null);
            }
        }
        return pics;
    }
}
