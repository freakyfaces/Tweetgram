package phase3.shared.events.authentication;

import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;


public class SignUpFormEvent extends Event {
    private String username;
    private String password;
    private String email;
    private String name;
    private String date;
    private String number;
    private String bio;
    public SignUpFormEvent(String username, String password, String email, String name
    , String date, String number, String bio) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.date = date;
        this.number = number;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.signUp(this);
    }
}
