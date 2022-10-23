package phase3.server.controller.authentication;

import org.apache.log4j.Logger;
import phase3.server.dataBase.Save;
import phase3.shared.events.authentication.SignUpFormEvent;
import phase3.shared.model.User;
import phase3.shared.response.authentication.SignUpResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static phase3.server.validation.CheckEmail.checkemail;
import static phase3.server.validation.CheckUsername.checkusername;

public class SignUp {
    public static org.apache.log4j.Logger logger= Logger.getLogger(SignUp.class);
    static public User user;
    public static DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("/yyyy/MM/dd/HH/mm/ss");
    public static SignUpResponse signUp(SignUpFormEvent signUpFormEvent){
        if (!checkusername(signUpFormEvent.getUsername()) && !checkemail(signUpFormEvent.getEmail())){
            user = new User(signUpFormEvent.getName(),signUpFormEvent.getUsername(),signUpFormEvent.getPassword(),
                    signUpFormEvent.getDate(), signUpFormEvent.getEmail(),signUpFormEvent.getNumber()
                    , signUpFormEvent.getBio());
            Save.saveData();
            logger.info(User.userList.getLast().Id +" Signed Up");
            String authToken = "id" + user.Id + LocalDateTime.now().format(dtformatter);
            return new SignUpResponse(authToken,"You Signed Up Successfully" );
        }
        else if (checkusername(signUpFormEvent.getUsername())){
            return new SignUpResponse("","This Username is Already Taken");
        }
        return new SignUpResponse("","This Email is Already Taken");
    }
}
