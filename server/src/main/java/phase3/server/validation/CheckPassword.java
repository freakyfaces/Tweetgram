package phase3.server.validation;

import phase3.shared.model.User;

import java.util.LinkedList;

public class CheckPassword {
    public static boolean checkpass(String username, String password, LinkedList<User> userList){
        for (User i: User.userList) {
            if (i.password.equals(password) && i.username.equals(username) && i.isactive){
                //AuthController.user = i;
                //CLI.state = "login";
                return true;
            }
        }
        return false;
    }

}
