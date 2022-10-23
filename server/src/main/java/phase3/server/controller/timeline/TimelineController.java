package phase3.server.controller.timeline;

import phase3.server.controller.authentication.Login;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

public class TimelineController {

    public static LinkedList<Tweet> timelineTweets = new LinkedList<>();
    public static org.apache.log4j.Logger logger= Logger.getLogger(TimelineController.class);
    public static LinkedList<Tweet> timeline(String authToken){
        User user1 = User.getUserByAuthToken(authToken);
        for (String following : user1.following) {
            User user = User.getUser(following);
            for (Tweet tweet : user.Tweets) {
                if (!timelineTweets.contains(tweet)){
                    timelineTweets.add(tweet);
                }
            }
        }
        Collections.sort(timelineTweets, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dt1 = new Date();
                Date dt2 = new Date();
                try {
                    dt1 = simpleDateFormat.parse(o1.datetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
                try {
                    dt2 = simpleDateFormat.parse(o2.datetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
                return dt2.compareTo(dt1);
            }
        });
        return timelineTweets;
    }
}