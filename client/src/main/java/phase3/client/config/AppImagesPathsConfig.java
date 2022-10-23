package phase3.client.config;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppImagesPathsConfig {
    public String account;
    public String setting;
    public String explore;
    public String timeline;
    public String messaging;

    public AppImagesPathsConfig() throws IOException {
        setProperties();
    }
    public PathsConfig paths = new PathsConfig();
    public void setProperties() throws IOException{
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(paths.imagesPathConfig);
        properties.load(fileReader);
        account = (String) properties.get("account");
        setting = (String) properties.get("setting");
        explore = (String) properties.get("explore");
        timeline = (String) properties.get("timeline");
        messaging = (String) properties.get("messaging");
    }
}
