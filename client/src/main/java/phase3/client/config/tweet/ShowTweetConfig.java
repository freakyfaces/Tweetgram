package phase3.client.config.tweet;


import phase3.client.config.PathsConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ShowTweetConfig {

    public int reportNumber;
    public String reportMessage;
    public String likeMessage;
    public String likedMessage;
    public String messageSent;
    public String noComments;
    public String retweetMessage;
    public String cantSend;
    public String tweetImgFormat;
    public String tweetInfo;
    public String dataFolder;
    public String tweetFolder;
    public ShowTweetConfig() throws IOException{
        setProperties();
    }
    public PathsConfig paths = new PathsConfig();
    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(paths.showTweetConfigPath);
        properties.load(fileReader);
        reportNumber = Integer.parseInt((String)properties.get("reportNumber"));
        reportMessage = (String) properties.get("reportMessage");
        likeMessage = (String) properties.get("likeMessage");
        likedMessage = (String) properties.get("likedMessage");
        messageSent = (String) properties.get("messageSent");
        noComments = (String) properties.get("noComments");
        retweetMessage = (String) properties.get("retweetMessage");
        cantSend = (String) properties.get("cantSend");
        tweetImgFormat = (String) properties.get("tweetImgFormat");
        tweetInfo = (String) properties.get("tweetInfo");
        dataFolder = (String) properties.get("dataFolder");
        tweetFolder = (String) properties.get("tweetFolder");
    }
}
