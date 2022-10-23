package phase3.shared.response.personalPage;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

public class ProfileInfoResponse extends Response {
    public String username;
    public String name;
    public String birthDate;
    public String email;
    public String phoneNumber;
    public String bio;
    public byte[] profilePicture;


    public ProfileInfoResponse(String username, String name, String birthDate, String email, String phoneNumber,
                               String bio, byte[] profilePicture) {

        this.username = username;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.profilePicture = profilePicture;

    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
