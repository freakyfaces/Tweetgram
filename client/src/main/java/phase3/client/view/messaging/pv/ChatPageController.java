package phase3.client.view.messaging.pv;


import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.shared.events.messaging.GetPvChatEvent;
import phase3.shared.events.messaging.GetSavedMessageEvent;
import phase3.shared.events.messaging.PvMessageActionEvent;
import phase3.shared.model.messaging.Message;
import phase3.shared.model.messaging.pvChat;
import phase3.client.view.LoadFXML;
import phase3.client.view.MainApp;
import phase3.client.view.messaging.MessagingPageController;
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
import phase3.shared.response.messaging.GetPvChatResponse;
import phase3.shared.util.Loop;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ChatPageController implements Initializable, ResponseVisitor<GetPvChatResponse> {
    public static Message message;
    public static byte[] messagePic;
    public static String path = "";
    Loop loop;
    LoadFXML loadFXML = new LoadFXML();
    public static pvChat pv ;
    public static LinkedList<byte[]> pics;
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
                    PvMessageActionEvent(MainApp.controller.authToken,pv.id,"send"
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
        MessageController.chatPageController = this;
        path = MessagingPageController.pvChatId;
        MainApp.controller.addEvent(new NewEvent(new GetPvChatEvent(MainApp.controller.authToken, path),
                this));
        loop = new Loop(0.4,()->
                MainApp.controller.addEvent(new NewEvent(new GetPvChatEvent(MainApp.controller.authToken, path),
                        this)));
        loop.start();
    }
    public void showMessages(){
        scrollPane.setContent(vbox);
        vbox.getChildren().clear();
        for (Message message1 : pv.messages) {
            messagePic = pics.get(pv.messages.indexOf(message1));
            message = message1;
            vbox.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.messagePath)));
        }
        scrollPane.setVvalue(scrollPane.getVmax());
    }

    @Override
    public void visit(GetPvChatResponse getPvChatResponse) {
        pv = getPvChatResponse.pvChat;
        pics = getPvChatResponse.pics;
        Platform.runLater(()->showMessages());
    }
}
