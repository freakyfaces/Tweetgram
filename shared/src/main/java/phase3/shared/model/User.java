package phase3.shared.model;

import phase3.shared.model.messaging.SavedMessage;
import phase3.shared.model.messaging.Category;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


public class User {
    public static DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dformatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static LinkedList<User> userList = new LinkedList<>();
    public static int LastId = 0;
    public transient LinkedList<Tweet> Tweets;
    public LinkedList<String> pvs;
    public LinkedList<String> following;
    public LinkedList<String> followers;
    public LinkedList<String> blacklist;
    public LinkedList<String> requests;
    public LinkedList<String> notifs;
    public LinkedList<String> mutelist;
    public LinkedList<Category> categories;
    public transient SavedMessage savedMessage;
    public String name;
    public String username;
    public String password;
    public String birthdate;
    public String email;
    public String number;
    public String bio;
    public String Id;
    public String pageState;
    public String lastseenState;
    public boolean isactive;
    private String lastseen;
    public int reportNumber;
    public String profilePicture;
    public LinkedList<Integer> groups;
    public User(String name, String username, String password, String birthdate,
                String email, String number, String bio){
        LastId++;
        this.Id = "Id"+LastId;
        this.groups = new LinkedList<>();
        this.profilePicture = "account.png";
        this.Tweets = new LinkedList<>();
        this.pvs = new LinkedList<>();
        this.username = username;
        this.name = name;
        this.password = password;
        this.birthdate = birthdate;
        this.categories = new LinkedList<>();
        this.notifs = new LinkedList<>();
        this.email = email;
        this.number = number;
        this.bio = bio;
        this.isactive = true;
        this.lastseen = LocalDateTime.now().format(dtformatter);
        this.following = new LinkedList<>();
        this.followers = new LinkedList<>();
        this.blacklist = new LinkedList<>();
        this.requests = new LinkedList<>();
        this.pageState = "public";
        this.lastseenState = "all"; // all, nobody, people you've followed
        this.reportNumber = 0;
        this.savedMessage = new SavedMessage(this.Id);
        this.mutelist = new LinkedList<>();
        userList.add(this);
    }
    public void addtweet(Tweet m){
        this.Tweets.add(m);
    }
    static int numberofusers(){
        return LastId;
    }
    public void setPassword(String npassword){
        this.password = npassword;
    }
    public void info(){
        System.out.println("name : "+this.name);
        System.out.println("username : "+this.username);
        System.out.println("email : "+this.email);
        System.out.println("phoneNumber : "+ this.number);
        System.out.println("birthDate : "+this.birthdate);
        System.out.println("bio : "+this.bio);
    }
    public String showlastseen(){
        return this.lastseen;
    }
    public void lastseennow(){
        this.lastseen = LocalDateTime.now().format(dtformatter);
    }
    public static String id2username(String id){
        for (User user : userList) {
            if (user.Id.equals(id)){
                return user.username;
            }
        }
        return "";
    }
    public static String username2id(String username){
        for (User user : userList) {
            if (user.username.equals(username)){
                return user.Id;
            }
        }
        return "";
    }
    public static User getUser(String id){
        for (User user : userList) {
            if (user.Id.equals(id) && user.isactive){
                return user;
            }
        }
        return userList.get(0);
    }
    public static User getUserByAuthToken(String authToken){
        String id = "";
        for (char c : authToken.substring(2).toCharArray()) {
            if (c == '/'){
                break;
            }
            else{
                id += c;
            }
        }
        return User.getUser(id);
    }
}
