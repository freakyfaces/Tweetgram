package phase3.server.dataBase;

import phase3.shared.model.User;

public class Context {
    public DBSet<User> Users = new UserDB();
}
