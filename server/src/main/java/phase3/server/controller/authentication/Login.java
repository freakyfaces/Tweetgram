package phase3.server.controller.authentication;

import org.apache.log4j.Logger;
import phase3.server.dataBase.Context;
import phase3.shared.events.authentication.LoginFormEvent;
import phase3.shared.model.User;
import phase3.shared.response.Response;
import phase3.shared.response.authentication.LoginResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import static phase3.server.validation.CheckPassword.checkpass;
import static phase3.server.validation.CheckUsername.checkusername;

public class Login {
    public static org.apache.log4j.Logger logger= Logger.getLogger(Login.class);

    Context context = new Context();
    static public User user;
    public static DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("/yyyy/MM/dd/HH/mm/ss");
    public LoginResponse login(LoginFormEvent loginFormEvent){
        LinkedList<User> all = context.Users.all();

        if (checkpass(loginFormEvent.getUsername(), loginFormEvent.getPassword(), all)){
            user = User.getUser(User.username2id(loginFormEvent.getUsername()));
            logger.info(User.username2id(loginFormEvent.getUsername())+" Logged in");
            String authToken = "id" + user.Id + LocalDateTime.now().format(dtformatter);
            return new LoginResponse("", authToken);
        }
        else{
            if (!checkusername(loginFormEvent.getUsername())){
                return new LoginResponse("This Username Does Not Exist!" ,"");

            }
        }
        return new LoginResponse("Password and Username Does Not Match!" ,"");
    }

}
