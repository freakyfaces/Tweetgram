package phase3.server.validation;

import phase3.shared.model.User;

public class CheckEmail {
    public static boolean checkemail(String email){
        for (User user : User.userList) {
            if (user.email.equals(email) && user.isactive) {
                return true;
            }
        }
        return false;
    }

}
