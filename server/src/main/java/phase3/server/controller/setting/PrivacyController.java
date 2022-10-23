package phase3.server.controller.setting;

import phase3.server.controller.authentication.Login;
import phase3.server.dataBase.Save;
import phase3.shared.events.setting.privacy.ChangePasswordEvent;
import org.apache.log4j.Logger;
import phase3.shared.model.User;
import phase3.shared.response.Response;
import phase3.shared.response.setting.privacy.ChangePasswordResponse;

public class PrivacyController {
    public static org.apache.log4j.Logger logger= Logger.getLogger(PrivacyController.class);

    public static void changeLastSeenState(String lastSeenState, User user){
        if (lastSeenState == null){
            return;
        }
        if (lastSeenState.equals("Everyone") ){
            user.lastseenState = "all";
        }
        else if(lastSeenState.equals("People You've Followed")){
            user.lastseenState = "people you've followed";
        }
        else if (lastSeenState.equals("Nobody")){
            user.lastseenState = "nobody";
        }
        logger.info(user.Id+" Changed Last Seen to "+lastSeenState);
        Save.saveData();
    }
    public static void changePageState(String pageState, User user){
        if (pageState == null){
            return;
        }
        if (pageState.equals("Public")){
            user.pageState = "public";
        }
        else if (pageState.equals("Private")){
            user.pageState = "private";
        }
        logger.info(user.Id+" Changed Page State to "+pageState);
        Save.saveData();
    }
    public static void isActive(String isActive, User user){
        if (isActive == null  || isActive.equals("Active") ){
            user.isactive = true;
        }
        else{
            user.isactive = false;
        }
        
        
        Save.saveData();
    }
    public static Response changePass(ChangePasswordEvent changePasswordEvent){
        User user = User.getUserByAuthToken(changePasswordEvent.authToken);
        if (user.password.equals(changePasswordEvent.oldPass) &&
        changePasswordEvent.newPass2.equals(changePasswordEvent.newPass1)){
            user.password = changePasswordEvent.newPass1;
            Save.saveData();
            logger.info(user.Id+" Changed Password");
            return new ChangePasswordResponse("Password Changed Successfully!");
        }
        if (!user.password.equals(changePasswordEvent.oldPass)){
            return new ChangePasswordResponse("You Entered Your Old Password Wrong!");

        }
        return new ChangePasswordResponse("Confirmation Failed!");

    }
}
