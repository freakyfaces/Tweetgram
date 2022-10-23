package phase3.client.view.personalPage.tweet;

import javafx.application.Platform;
import phase3.client.model.NewEvent;
import phase3.client.view.MainApp;
import phase3.shared.events.personalPage.ShareTweetEvent;
import phase3.shared.response.ResponseVisitor;
import phase3.shared.response.personalPage.ShareTweetResponse;
import java.io.IOException;

public class TweetStringListener implements ResponseVisitor<ShareTweetResponse> {
    public static String test;
    public TweetController controller1;
    public void StringEventOccurred(TweetController controller, ShareTweetEvent event) throws IOException {
        this.controller1 = controller;
        MainApp.controller.addEvent(new NewEvent(event, this));

    }

    @Override
    public void visit(ShareTweetResponse shareTweetResponse) {
        Platform.runLater(()-> controller1.setStatus(shareTweetResponse.message));
    }
}
