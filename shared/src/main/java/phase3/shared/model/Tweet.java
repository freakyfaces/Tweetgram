package phase3.shared.model;




import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Tweet {
    public static DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dformatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public String text;
    public LinkedList<Tweet> comments;
    public String datetime;
    public LinkedList<String> likes;
    public String Id;
    public String tweetId;
    public int reportNumber;
    public Tweet(String text, String Id) {
        this.Id = Id;
        this.text = text;
        this.reportNumber = 0;
        this.likes = new LinkedList<>();
        this.comments = new LinkedList<>();
        this.tweetId = ""+(1+User.getUser(Id).Tweets.size());
        this.datetime = LocalDateTime.now().format(dtformatter);
    }
    public Tweet(Tweet tweet){
        this.Id = tweet.Id;
        this.text = tweet.text;
        this.likes = tweet.likes;
        this.comments = tweet.comments;
        this.datetime = tweet.datetime;
        this.tweetId = tweet.tweetId;
        this.reportNumber = 0;
    }
    void edit(String text){
        this.text = text;
    }
    public String out(){
        return this.text + "--- tweeted at " + this.datetime +" by "+ User.id2username(this.Id);
    }
    void showcms(){
        for (Tweet i : this.comments) {
            i.out();
        }
    }

}
