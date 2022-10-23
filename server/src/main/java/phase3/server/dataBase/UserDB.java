package phase3.server.dataBase;

import phase3.shared.model.User;

import java.util.LinkedList;

public class UserDB implements DBSet<User> {
    @Override
    public User get(String id) {
        return User.getUser(id);
    }
    public String id2username(String id){
        for (User user : User.userList) {
            if (user.Id.equals(id)){
                return user.username;
            }
        }
        return "";
    }
    public String username2id(String username){
        for (User user : User.userList) {
            if (user.username.equals(username)){
                return user.Id;
            }
        }
        return "";
    }
    @Override
    public LinkedList<User> all() {
        return User.userList;
    }
    @Override
    public void add(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void update(User user) {

    }
}
