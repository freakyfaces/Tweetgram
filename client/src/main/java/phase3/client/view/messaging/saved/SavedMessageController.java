package phase3.client.view.messaging.saved;



import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.shared.events.messaging.GetSavedMessageEvent;
import phase3.shared.events.messaging.SavedMessageActionEvent;
import phase3.shared.model.messaging.Message;
import phase3.client.view.LoadFXML;
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
import phase3.client.view.MainApp;
import phase3.shared.model.messaging.SavedMessage;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.messaging.GetSavedMessageResponse;
import phase3.shared.util.Loop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class SavedMessageController implements Initializable, ResponseVisitor<GetSavedMessageResponse> {
    public static Message message;
    LoadFXML loadFXML = new LoadFXML();
    public SavedMessage savedMessage;
    public LinkedList<byte[]> savedMessagePics;
    public static byte[] messagePic;
    public static Loop loop;
    public byte[] sendMessagePic;
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
                    SavedMessageActionEvent(MainApp.controller.authToken,"send"
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
        SMessageController.controller = this;
        MainApp.controller.addEvent(new NewEvent(new GetSavedMessageEvent(MainApp.controller.authToken),
                this));
        loop = new Loop(0.4,()->
                MainApp.controller.addEvent(new NewEvent(new GetSavedMessageEvent(MainApp.controller.authToken),
                        this)));
        loop.start();
    }
    public void showMessages(){
        scrollPane.setContent(vbox);
        vbox.getChildren().clear();
        for (int i = 0; i < savedMessage.messages.size() ; i++) {
            messagePic = savedMessagePics.get(i);
            message = savedMessage.messages.get(i);
            vbox.getChildren().add(new HBox(loadFXML.loadFXMl(MainApp.paths.savedMessagePath)));
        }
    }

    @Override
    public void visit(GetSavedMessageResponse getSavedMessageResponse) {
        savedMessage = getSavedMessageResponse.savedMessage;
        savedMessagePics = getSavedMessageResponse.pics;
        Platform.runLater(()->showMessages());
    }
}
