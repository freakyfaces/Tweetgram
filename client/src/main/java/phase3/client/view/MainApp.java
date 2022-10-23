package phase3.client.view;

import phase3.client.config.PathsConfig;
import phase3.client.controller.network.SocketEventSender;
import phase3.client.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import phase3.client.model.NewEvent;
import phase3.shared.config.Server;
import phase3.shared.events.authentication.CloseAppEvent;

import java.io.IOException;
import java.net.Socket;

public class MainApp extends Application {
    public static org.apache.log4j.Logger logger= Logger.getLogger(MainApp.class);
    public static PathsConfig paths;
    public static MainController controller ;
    static {
        try {
            paths = new PathsConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Socket socket = new Socket("127.0.0.1", Server.serverAddress);
        controller = new MainController(new SocketEventSender(socket));
        controller.start();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(paths.startPagePath));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            event.consume();
            logger.info("App Closed");
            exit(stage);
        });
    }

    public static void main(String[] args) {
        logger.info("App Started");
        launch(args);
    }
    public void exit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Are you sure you want to exit?");
        if (alert.showAndWait().get() == ButtonType.OK){
            controller.addEvent(new NewEvent(new CloseAppEvent(controller.authToken),null));
            stage.close();
        }
    }
}
