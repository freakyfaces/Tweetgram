package phase3.client.config.explorer;



import phase3.client.config.PathsConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProfileConfig {
    public PathsConfig paths = new PathsConfig();
    public String followed;
    public String notFollowed;
    public String report;
    public String block;
    public String unblock;
    public String lastSeenRecently;
    public String unfollow;
    public String sendReq;
    public String reqSend;
    public String publicPage;
    public String privatePage;
    public String cantMessage;
    public String follow;
    public String reported;
    public String youAreBlocked;
    public ProfileConfig() throws IOException {
        setProperties();
    }
    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(paths.profileConfigPath);
        properties.load(fileReader);
        followed = (String) properties.get("followed");
        notFollowed = (String) properties.get("notFollowed");
        report = (String) properties.get("report");
        reported = (String) properties.get("reported");
        block = (String) properties.get("block");
        unblock = (String) properties.get("unblock");
        lastSeenRecently = (String) properties.get("lastSeenRecently");
        unfollow = (String) properties.get("unfollow");
        sendReq = (String) properties.get("sendReq");
        reqSend = (String) properties.get("reqSend");
        publicPage = (String) properties.get("publicPage");
        privatePage = (String) properties.get("privatePage");
        cantMessage = (String) properties.get("cantMessage");
        follow = (String) properties.get("follow");
        youAreBlocked = (String) properties.get("youAreBlocked");
    }
}
