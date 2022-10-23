package phase3.server.controller.personalPage;

import phase3.server.dataBase.Load;
import phase3.shared.model.User;
import phase3.shared.response.personalPage.ProfileInfoResponse;

import java.io.IOException;

public class ProfileInfo {

    public static ProfileInfoResponse profileInfoResponse (String authToken) throws IOException {

        User user = User.getUserByAuthToken(authToken);

        byte[] profile = Load.picToByte(user.Id);

        return new ProfileInfoResponse(user.username, user.name, user.birthdate, user.email, user.number,
                user.bio, profile);

    }



}
