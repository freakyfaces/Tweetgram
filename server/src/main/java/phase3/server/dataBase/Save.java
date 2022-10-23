package phase3.server.dataBase;

import phase3.shared.model.messaging.GroupChat;
import phase3.shared.model.User;
import phase3.shared.model.messaging.pvChat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.LinkedList;


public class Save {
    static GsonBuilder gsonBuilder = new GsonBuilder();
    public static Gson gson = gsonBuilder.setPrettyPrinting().create();
    public static org.apache.log4j.Logger logger= Logger.getLogger(Save.class);
    public static void saveData(){
        try {
            FileWriter writer = new FileWriter("server/data/Users.json");
            gson.toJson(User.userList, writer);
            writer.flush();
            writer.close();
            writer = new FileWriter("server/data/Groups.json");
            gson.toJson(GroupChat.groupChats, writer);
            writer.flush();
            writer.close();
            for (User user : User.userList) {
                saveList(user, user.Tweets, user.Id + "/Tweets");
                File file = new File("server/data/"+user.Id);
                if (!file.exists()){
                    file.mkdirs();
                }
                FileWriter nWriter = new FileWriter("server/data/"+user.Id+"/savedMessage"+user.Id+".json");
                gson.toJson(user.savedMessage,nWriter);
                nWriter.flush();
                nWriter.close();
            }
        }
        catch (IOException e){
            logger.error( "error in saving data");
            e.printStackTrace();
        }
    }
    static void saveList(User activeUser, LinkedList list, String name){
        try {
            File file = new File("server/data/"+activeUser.Id);
            if (!file.exists()){
                file.mkdirs();
            }
            FileWriter writer = new FileWriter("server/data/"+name+activeUser.Id+".json");
            gson.toJson(list, writer);
            writer.flush();
            writer.close();

        }
        catch (IOException e){
            logger.error("error in saving data");
            e.printStackTrace();
        }
    }
    public static void savePvChat(pvChat pvChat1) throws IOException {
        File file = new File("server/data/pvChats/"+pvChat1.id);
        if (!file.exists()){
            file.mkdirs();
        }
        FileWriter nWriter = new FileWriter("server/data/pvChats/"+pvChat1.id+"/"+pvChat1.id+".json");
        gson.toJson(pvChat1,nWriter);
        logger.info("PvChat Saved");
        nWriter.flush();
        nWriter.close();
    }

    public static void saveProfilePic(byte[] profileBytes, String userId) throws IOException {
        if (profileBytes != null) {
            try {
                FileOutputStream stream = new FileOutputStream("server/data/" + userId+"/ProfilePicture.jpg");
                stream.write(profileBytes);
            } catch (IOException e) {
                logger.error("Error in Saving picture");
            }
        }
    }
    public static void saveTweetPic(byte[] tweetPic, String userId){
        try {
            if (tweetPic != null) {
                File test = new File("server/data/" + userId + "/TweetsPictures");
                if (!test.exists()) {
                    test.mkdirs();
                }
                FileOutputStream stream = new FileOutputStream("server/data/" + userId + "/TweetsPictures/"
                        + (User.getUser(userId).Tweets.size()) + ".jpg");
                stream.write(tweetPic);
            }
        } catch (Exception e) {
            logger.error("Error in Saving picture");
        }
    }
    public static void saveSavedMessagePic(byte[] pic, String authToken){
        User user = User.getUserByAuthToken(authToken);
        try {
            File file1 = new File("server/data/"+user.Id+"/SavedMessagesPics/");
            if (!file1.exists()){
                file1.mkdirs();
            }
            FileOutputStream stream = new FileOutputStream("server/data/"+user.Id+"/SavedMessagesPics/"
                    +(user.savedMessage.messages.size())+".jpg");
            stream.write(pic);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void savePvMessagePic(byte[] pic, String pvId) throws FileNotFoundException {
        pvChat pvChat = Load.loadPvChat(pvId);
        try {
            File file1 = new File("server/data/pvChats/"+pvChat.id);
            if (!file1.exists()){
                file1.mkdirs();
            }
            FileOutputStream stream = new FileOutputStream("server/data/pvChats/"+pvChat.id+"/"
                    +pvChat.messages.size()+".jpg");
            stream.write(pic);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveGroupChatPic(byte[] pic, String groupId) throws FileNotFoundException {
        try {
            File file1 = new File("server/data/Groups/"+groupId);
            if (!file1.exists()){
                file1.mkdirs();
            }
            FileOutputStream stream = new FileOutputStream("server/data/Groups/"+groupId+"/"
                    +GroupChat.groupChats.get(Integer.parseInt(groupId)).messages.size()+".jpg");
            stream.write(pic);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

