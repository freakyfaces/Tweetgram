package phase3.shared.events.authentication;


import phase3.shared.events.Event;
import phase3.shared.events.EventVisitor;
import phase3.shared.response.Response;



public class LoginFormEvent extends Event {
    private String username;
    private String password;
    public LoginFormEvent( String username, String password){
        this.username = username;
        this.password = password;
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

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.login(this);
    }
}
