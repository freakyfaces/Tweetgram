package phase3.server.controller.personalPage;

import phase3.server.controller.Controller;
import phase3.server.dataBase.Save;
import phase3.server.dataBase.UserDB;
import phase3.shared.events.personalPage.EditProfileFormEvent;
import phase3.server.validation.CheckEmail;
import phase3.server.validation.CheckUsername;
import org.apache.log4j.Logger;
import phase3.shared.model.User;
import phase3.shared.response.personalPage.EditProfileResponse;

import java.io.IOException;


public class EditProfileController extends Controller {
    UserDB userDB = new UserDB();
    public static org.apache.log4j.Logger logger= Logger.getLogger(EditProfileController.class);
    public EditProfileResponse EditProfile(EditProfileFormEvent formEvent) throws IOException {
        User user = User.getUserByAuthToken(formEvent.authToken);
        if (CheckUsername.checkusername(formEvent.getUsername()) && !(userDB.username2id(formEvent.getUsername())
                .equals(user.Id))){
            return new EditProfileResponse("This Username is Already Taken!");
        }
        else if (CheckEmail.checkemail(formEvent.getEmail()) && !formEvent.getEmail().equals(user.email)){
            return new EditProfileResponse("This Email is Already Taken!");
        }
        user.username = formEvent.getUsername();
        user.name = formEvent.getName();
        user.email = formEvent.getEmail();
        user.bio = formEvent.getBio();
        user.birthdate = formEvent.getBirthDate();
        user.number = formEvent.getNumber();
        Save.saveProfilePic(formEvent.profilePic,user.Id);
        Save.saveData();
        logger.info(user.Id+"Changed info");
        return new EditProfileResponse("Changes Saved Successfully!");
    }
}
