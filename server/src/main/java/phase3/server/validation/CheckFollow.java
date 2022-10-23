package phase3.server.validation;

import phase3.server.controller.authentication.Login;
import phase3.server.dataBase.Log;
import phase3.shared.model.User;

public class CheckFollow {
    public static boolean checkFollowing (String username){
        for (String s : Login.user.following) {
            if (s.equals(User.username2id(username))){
                return true;
            }
        }
        return false;
    }

    public static boolean checkFollowers(String username){
        for (String s : Login.user.followers) {
            if (s.equals(User.username2id(username))){
                return true;
            }
        }
        return false;
    }
}
