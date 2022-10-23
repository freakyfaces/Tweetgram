package phase3.client.listener;



import javafx.stage.Stage;

public interface FormListener<T> {
    void EventOccurred(Stage stage, T formEvent);
}
