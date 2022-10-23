package phase3.server.dataBase;

import java.util.LinkedList;

public interface DBSet<T> {
    T get(String id);

    LinkedList<T> all();
    void add(T t);
    void remove(T t);
    void update(T t);
}