package phase3.client.view.messaging.groups;



import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.shared.events.messaging.GetGroupEvent;
import phase3.shared.events.messaging.GroupMessageActionEvent;
import phase3.shared.events.messaging.PvMessageActionEvent;
import phase3.shared.model.messaging.GroupChat;
import phase3.shared.model.messaging.Message;
import phase3.client.view.LoadFXML;
import phase3.client.view.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.messaging.GetGroupResponse;
import phase3.shared.util.Loop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class GroupPageController implements Initializable, ResponseVisitor<GetGroupResponse> {
    LoadFXML loadFXML = new LoadFXML();
    public static byte[] messagePic;
    public static Message message;
    public static String groupId;
    public GetGroupResponse response;
    @FXML
    private javafx.scene.control.Button send;
    @FXML
    private Button archive;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField messageText;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView imageView;

    @FXML
    public void send() throws IOException {
        if ((!messageText.getText().equals("")) || imageView.getImage() != null){
            MainApp.controller.addEvent(new NewEvent(new
                    GroupMessageActionEvent(MainApp.controller.authToken,groupId,"send"
                    ,messageText.getText(),"",messagePic),null));
            imageView.setImage(null);
            messagePic = null;
            messageText.setText("");
        }
    }
    @FXML
    public void archive() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("JPG files (*.jpg)",
                "*.jpg");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            javafx.scene.image.Image myImage = new Image(file.toURI().toString(), false);
            imageView.setImage(myImage);
            BufferedImage originalImage= ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", byteArrayOutputStream );
            messagePic = byteArrayOutputStream.toByteArray();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        groupId = GroupOverviewController.groupChat;
        GroupMessage.controller = this;
        Loop loop = new Loop(0.2, ()-> MainApp.controller.addEvent(
                new NewEvent(new GetGroupEvent(MainApp.controller.authToken, groupId),this)));
        loop.start();

    }
    public void showMessages(){
        scrollPane.setContent(vbox);
        vbox.getChildren().clear();
        for (Message message1 : response.groupChat.getMessages()) {
            message = message1;
            messagePic = response.pics.get(response.groupChat.getMessages().indexOf(message1));
            vbox.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.groupMessagePath)));
        }
        scrollPane.setVvalue(scrollPane.getVmax());
    }

    @Override
    public void visit(GetGroupResponse getGroupResponse) {
        this.response = getGroupResponse;
        Platform.runLater(()->showMessages());
    }
}
