package phase3.client.config;



import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CategoryStates {
    public String done;
    public String userDoesntExist;
    public String categoryDoesntExist;
    public String sent;
    public String cant;
    public PathsConfig paths = new PathsConfig();
    public CategoryStates() throws IOException {
        setProperties();
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(paths.categoryStatesPath);
        properties.load(fileReader);
        done = (String) properties.get("done");
        userDoesntExist = (String) properties.get("userDoesntExist");
        categoryDoesntExist = (String) properties.get("categoryDoesntExist");
        sent = (String) properties.get("sent");
        cant = (String) properties.get("cant");
    }
}
