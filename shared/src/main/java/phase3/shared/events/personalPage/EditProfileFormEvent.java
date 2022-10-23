package phase3.shared.events.personalPage;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;

import java.util.EventObject;

public class EditProfileFormEvent extends Event {
    public String authToken;
    public String name;
    public String username;
    public String email;
    public String birthDate;
    public String bio;
    public String number;
    public byte[] profilePic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public EditProfileFormEvent(String name, String username, String email, String number,
                                String birthDate, String bio, byte[] profilePic, String authToken){

        this.name = name;
        this.username = username;
        this.email = email;
        this.number = number;
        this.birthDate = birthDate;
        this.bio = bio;
        this.profilePic = profilePic;
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.editProfile(this);
    }
}
