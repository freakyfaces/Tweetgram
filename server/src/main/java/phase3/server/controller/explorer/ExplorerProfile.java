package phase3.server.controller.explorer;

import phase3.server.Main;
import phase3.server.dataBase.Load;
import phase3.shared.events.explorer.GetProfileEvent;
import phase3.shared.events.explorer.ProfilePageActionsEvent;
import phase3.shared.model.User;
import phase3.shared.response.explorer.GetProfileResponse;
import phase3.shared.response.explorer.ProfileActionResponse;

import java.io.IOException;

public class ExplorerProfile {

    public static GetProfileResponse getProfileResponse(GetProfileEvent event) throws IOException {
        User user = User.getUserByAuthToken(event.authToken);
        User user2 = User.getUser(User.username2id(event.username));
        String followState;
        if (user.following.contains(User.username2id(event.username))){
            followState = "Followed";
        }
        else {
            followState = "Not Followed";
        }
        String lastSeen;
        if (user2.username.equals(user.username)){
            lastSeen = "Online";
        }
        else if (user2.lastseenState.equals("nobody") || (user2.lastseenState.equals("people you've followed")
                && !user2.following.contains(user.Id))){
            lastSeen = "Not Visible";
        }
        else {
            lastSeen = user2.showlastseen();
            for (String onlineUser : Main.onlineUsers) {
                if (onlineUser.startsWith("id" + user2.Id)) {
                    lastSeen = "Online";
                    break;
                }
            }
        }
        String block = "Block";
        if (user.blacklist.contains(user2.Id)){
            block = "Unblock";
        }
        String followBtn;
        if (user.following.contains(user2.Id)){
            followBtn = "Unfollow";
        }

        else if (user2.requests.contains(user.Id)){
            followBtn = "Request Sent!";
        }
        else {
            followBtn = "Follow";
        }
        byte[] profilePic = Load.picToByte(User.username2id(event.username));
        return new GetProfileResponse(profilePic,user2.name, user2.username, lastSeen,followState,
                block, followBtn,user2.pageState);

    }
    public static ProfileActionResponse profileAction(ProfilePageActionsEvent event){
        User user = User.getUserByAuthToken(event.authToken);
        ProfileActionResponse response ;
        if (event.action.equals("Follow")){
            if (event.username.equals(user.username)){
                response = new ProfileActionResponse("You Can't Follow Yourself","Follow");
                return response;
            }
            if (User.getUser(User.username2id(event.username)).pageState.equals("public") &&
                    !User.getUser(User.username2id(event.username)).blacklist.contains(user.Id) &&
                    !User.getUser(User.username2id(event.username)).followers.contains(user.Id)){
                user.following.add(User.username2id(event.username));
                User.getUser(User.username2id(event.username)).followers.add(user.Id);
                User.getUser(User.username2id(event.username)).notifs.add(user.Id+" follow");
                response = new ProfileActionResponse("","Unfollow");
                return response;
            }
            else if(User.getUser(User.username2id(event.username)).blacklist.contains(user.Id)){
                response = new ProfileActionResponse("","Unfollow");
                return response;
            }
            if (!User.getUser(User.username2id(event.username)).blacklist.contains(user.Id) &&
                    !User.getUser(User.username2id(event.username)).followers.contains(user.Id)){
                if (!User.getUser(User.username2id(event.username)).requests.contains(user.Id)) {
                    User.getUser(User.username2id(event.username)).requests.add(user.Id);
                }
                response = new ProfileActionResponse("","Request Sent!");
            }
            else{
                response = new ProfileActionResponse("Sorry, You Are Blocked By This User.",
                        "Send Request");
            }
            return response;
        }
        else if(event.action.equals("Unfollow")){
            user.following.remove(User.username2id(event.username));
            User.getUser(User.username2id(event.username)).followers.remove(user.Id);
            String btn = "Follow";
            User.getUser(User.username2id(event.username)).notifs.add(user.Id+" unfollow");
            if (User.getUser(User.username2id(event.username)).pageState.equals("private")){
                btn = "Send Request";
            }
            return new ProfileActionResponse("", btn);
        }
        else if(event.action.equals("Report")){
            if (event.username.equals(user.username)){
                return new ProfileActionResponse("You Can't Report Yourself", "Report");
            }
            User.getUser(User.username2id(event.username)).reportNumber++;
            response = new ProfileActionResponse("", "Report");
            return response;
        }
        else if(event.action.equals("Block")){
            if (user.username.equals(event.username)){
                return new ProfileActionResponse("You Can't Block Yourself", "Block");
            }
            if (user.following.contains(User.username2id(event.username))){
                user.following.remove(User.username2id(event.username));
                User.getUser(User.username2id(event.username)).followers.remove(user.Id);
            }
            if (user.followers.contains(User.username2id(event.username))){
                user.followers.remove(User.username2id(event.username));
                User.getUser(User.username2id(event.username)).following.remove(user.Id);
            }
            user.blacklist.add(User.username2id(event.username));
            response = new ProfileActionResponse("","Unblock");
            return response;

        }
        else if(event.action.equals("Unblock") && user.blacklist.contains(User.username2id(event.username))){
            user.blacklist.remove(User.username2id(event.username));
            response = new ProfileActionResponse("","Block");
            return response;
        }
        return null;
    }
}
