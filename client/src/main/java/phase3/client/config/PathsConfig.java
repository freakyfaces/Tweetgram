package phase3.client.config;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PathsConfig {
    private String pathsConfigPath =
            "src/main/resources/phase3/client/config/FxmlPathsConfig.properties";

    public String showTweetConfigPath;
    public String loginPagePath;
    public String signUpPagePath;
    public String startPagePath;
    public String exploreTweetPath;
    public String chatPagePath;
    public String contactPath;
    public String messagePath;
    public String messagingPagePath;
    public String savedMessagePath;
    public String savedMessagePagePath;
    public String editProfilePath;
    public String listsProfilePath;
    public String listsPath;
    public String notificationsPagePath;
    public String requestPath;
    public String profileInfoPath;
    public String personalPageShowTweetPath;
    public String personalPageTweetPath;
    public String personalPagePath;
    public String privacyPagePath;
    public String settingPagePath;
    public String timelinePagePath;
    public String timelineTweetPath;
    public String mainPagePath;
    public String profilePagePath;
    public String explorerPagePath;
    public String imagesPathConfig;
    public String profileConfigPath;
    public String groupItemPath;
    public String groupOverviewPath;
    public String groupMessagePath;
    public String groupChatPath;
    public String categoriesOverviewPath;
    public String categoriesItemPath;
    public String categoryStatesPath;

    public PathsConfig() throws IOException {
        setProperties();
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(pathsConfigPath);
        properties.load(fileReader);
        showTweetConfigPath = (String) properties.get("showTweetConfigPath");
        loginPagePath = (String) properties.get("loginPagePath");
        signUpPagePath = (String) properties.get("signUpPagePath");
        startPagePath = (String) properties.get("startPagePath");
        exploreTweetPath = (String) properties.get("exploreTweetPath");
        profilePagePath = (String) properties.get("profilePagePath");
        explorerPagePath = (String) properties.get("explorerPagePath");
        chatPagePath = (String) properties.get("chatPagePath");
        contactPath = (String) properties.get("contactPath");
        messagePath = (String) properties.get("messagePath");
        messagingPagePath = (String) properties.get("messagingPagePath");
        savedMessagePath = (String) properties.get("savedMessagePath");
        savedMessagePagePath = (String) properties.get("savedMessagePagePath");
        editProfilePath = (String) properties.get("editProfilePath");
        listsProfilePath = (String) properties.get("listsProfilePath");
        listsPath = (String) properties.get("listsPath");
        notificationsPagePath = (String) properties.get("notificationsPagePath");
        requestPath = (String) properties.get("requestPath");
        profileInfoPath = (String) properties.get("profileInfoPath");
        personalPageShowTweetPath = (String) properties.get("personalPageShowTweetPath");
        personalPageTweetPath = (String) properties.get("personalPageTweetPath");
        personalPagePath = (String) properties.get("personalPagePath");
        privacyPagePath = (String) properties.get("privacyPagePath");
        settingPagePath = (String) properties.get("settingPagePath");
        timelinePagePath = (String) properties.get("timelinePagePath");
        timelineTweetPath = (String) properties.get("timelineTweetPath");
        mainPagePath = (String) properties.get("mainPagePath");
        imagesPathConfig = (String) properties.get("imagesPathConfig");
        profileConfigPath = (String) properties.get("profileConfigPath");
        groupItemPath = (String) properties.get("groupItemPath");
        groupOverviewPath = (String) properties.get("groupOverviewPath");
        groupMessagePath = (String) properties.get("groupMessagePath");
        groupChatPath = (String) properties.get("groupChatPath");
        categoriesOverviewPath = (String) properties.get("categoriesOverviewPath");
        categoriesItemPath = (String) properties.get("categoriesItemPath");
        categoryStatesPath = (String) properties.get("categoryStatesPath");
    }

}