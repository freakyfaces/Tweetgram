package phase3.client.listener;

import java.io.IOException;

public interface StringListener<T> {
    void StringEventOccurred(T window, String string) throws IOException;
}
