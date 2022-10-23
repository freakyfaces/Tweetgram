package phase3.server.controller.setting;

import phase3.server.dataBase.Save;
import phase3.shared.model.User;
import org.apache.log4j.Logger;

public class DeleteAccountController {
    public static org.apache.log4j.Logger logger= Logger.getLogger(DeleteAccountController.class);

    public static void deleteAccount(User user){
        user.isactive = false;
        logger.info(user.Id +" Deleted Account");
        Save.saveData();
    }
}
