package phase3.server.validation;

import phase3.shared.model.User;

public class CheckUsername {
    public static boolean checkusername(String username){
        for (User user : User.userList) {
            if (user.username.equals(username) && user.isactive) {
                return true;
            }
        }
        return false;
    }
}
