package phase3.server.controller;


import phase3.server.Main;
import phase3.server.controller.authentication.Login;
import phase3.server.controller.authentication.SignUp;
import phase3.server.controller.explorer.ExplorerProfile;
import phase3.server.controller.messaging.CategoryController;
import phase3.server.controller.messaging.GroupController;
import phase3.server.controller.messaging.PvsController;
import phase3.server.controller.personalPage.*;
import phase3.server.controller.setting.PrivacyController;
import phase3.server.controller.timeline.TimelineController;
import phase3.server.dataBase.Load;
import phase3.server.dataBase.Save;
import phase3.server.validation.CheckFollow;
import phase3.server.validation.CheckUsername;
import phase3.shared.events.EventVisitor;
import phase3.shared.events.GetTweetEvent;
import phase3.shared.events.authentication.CloseAppEvent;
import phase3.shared.events.messaging.*;
import phase3.shared.events.setting.privacy.LogOutEvent;
import phase3.shared.events.authentication.LoginFormEvent;
import phase3.shared.events.authentication.SignUpFormEvent;
import phase3.shared.events.explorer.GetProfileEvent;
import phase3.shared.events.explorer.ProfilePageActionsEvent;
import phase3.shared.events.personalPage.*;
import phase3.shared.events.personalPage.notifications.NotificationsEvent;
import phase3.shared.events.personalPage.notifications.RequestEvent;
import phase3.shared.events.setting.privacy.ChangePasswordEvent;
import phase3.shared.events.setting.privacy.PrivacySettingEvent;
import phase3.shared.events.timeLine.TimeLineTweetsEvent;
import phase3.shared.model.Tweet;
import phase3.shared.model.User;
import phase3.shared.model.messaging.*;
import phase3.shared.response.Response;
import phase3.shared.response.TweetResponse;
import phase3.shared.response.authentication.LoginResponse;
import phase3.shared.response.authentication.SignUpResponse;
import phase3.shared.response.explorer.GetProfileResponse;
import phase3.shared.response.messaging.*;
import phase3.shared.response.personalPage.GetTweetsResponse;
import phase3.shared.response.setting.privacy.PrivacySettingResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class ClientHandler extends Thread implements EventVisitor {
    private final ResponseSender sender;
    public String authToken;
    public static DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("/yyyy/MM/dd/HH/mm/ss");
    public User user;

    public ClientHandler(ResponseSender sender) throws IOException {

        this.sender = sender;

    }

    public void run() {
        while (true) {
            sender.sendResponse(sender.getEvent().visit(this));
        }
    }

    @Override
    public Response signUp(SignUpFormEvent signUpFormEvent) {
        SignUpResponse response = SignUp.signUp(signUpFormEvent);
        authToken = response.authToken;
        if (!authToken.equals("")){
            Main.onlineUsers.add(authToken);
        }
        return response;
    }

    @Override
    public Response login(LoginFormEvent loginFormEvent) {
        Login login = new Login();
        LoginResponse response = login.login(loginFormEvent);
        authToken = response.authToken;
        if (!authToken.equals("")){
            Main.onlineUsers.add(authToken);
        }
        return response;
    }

    @Override
    public Response changePassword(ChangePasswordEvent changePasswordEvent) {
        if (changePasswordEvent.authToken.equals(authToken)){
            return PrivacyController.changePass(changePasswordEvent);
        }
        return null;
    }

    @Override
    public Response privacySetting(PrivacySettingEvent privacySettingEvent) {
        if (privacySettingEvent.authToken.equals(authToken)){
            User user = User.getUserByAuthToken(authToken);
            PrivacyController.changeLastSeenState(privacySettingEvent.lastSeen, user);
            PrivacyController.changePageState(privacySettingEvent.pageState, user);
            PrivacyController.isActive(privacySettingEvent.isActive, user);
            return new PrivacySettingResponse(user.lastseenState , user.pageState,
                    user.isactive+"") ;
        }
        return null;
    }

    @Override
    public Response profileInfo(ProfileInfoEvent profileInfoEvent) {

        if (authToken.equals(profileInfoEvent.authToken)){
            try {
                return ProfileInfo.profileInfoResponse(profileInfoEvent.authToken);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Response editProfile(EditProfileFormEvent editProfileFormEvent) {
        EditProfileController controller = new EditProfileController();
        if (editProfileFormEvent.authToken.equals(authToken)){
            try {
                return controller.EditProfile(editProfileFormEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Response notifications(NotificationsEvent notificationsEvent) {
        if (authToken.equals(notificationsEvent.authToken)){
            return NotificationPageController.notifications(notificationsEvent.authToken);
        }
        return null;
    }

    @Override
    public Response request(RequestEvent requestEvent) {
        if (authToken.equals(requestEvent.authToken)){
            return NotificationPageController.requests(requestEvent);
        }
        return null;
    }

    @Override
    public Response getProfile(GetProfileEvent getProfileEvent) {
        if (authToken.equals(getProfileEvent.authToken) && (CheckUsername.checkusername(getProfileEvent.username) ||
                getProfileEvent.username.startsWith("Id"))){
            if (getProfileEvent.username.startsWith("Id")){
                getProfileEvent.username = User.id2username(getProfileEvent.username);
                try {
                    return ExplorerProfile.getProfileResponse(getProfileEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                return ExplorerProfile.getProfileResponse(getProfileEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!CheckUsername.checkusername(getProfileEvent.username)){
            return new GetProfileResponse("User Not Found");
        }
        return null;
    }

    @Override
    public Response profileAction(ProfilePageActionsEvent profilePageActionsEvent) {
        if (authToken.equals(profilePageActionsEvent.authToken)){
            return ExplorerProfile.profileAction(profilePageActionsEvent);
        }
        return null;
    }

    @Override
    public Response getLists(ListsEvent listsEvent) {
        if (authToken.equals(listsEvent.authToken)){
            try {
                return GetLists.getLists(listsEvent.authToken);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Response shareTweet(ShareTweetEvent shareTweetEvent) {
        if (authToken.equals(shareTweetEvent.authToken)){
            try {
                return ShareTweetController.ShareTweet(shareTweetEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Response getTweets(GetTweetsEvent getTweetsEvent) {
        if (authToken.equals(getTweetsEvent.authToken)){
            User user = User.getUserByAuthToken(getTweetsEvent.authToken);
            LinkedList<Tweet> mainTweets;
            if (getTweetsEvent.subTweet == null){
                mainTweets = user.Tweets;
            }
            else{
                mainTweets = getTweetsEvent.subTweet.comments;
            }
            LinkedList<Tweet> tweets = new LinkedList<>();
            LinkedList<byte[]> tweetsPic = new LinkedList<>();
            LinkedList<byte[]> profilePic = new LinkedList<>();
            for (int i = 0; i < mainTweets.size(); i++) {
                try {
                    tweets.add(mainTweets.get(i));
                    tweetsPic.add(Load.loadTweetPic(i+1+"",user.Id));
                    profilePic.add(Load.picToByte(mainTweets.get(i).Id));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new GetTweetsResponse(tweets, tweetsPic, profilePic);
        }
        return null;
    }

    @Override
    public Response tweetAction(TweetActionEvent tweetActionEvent) {
        if (authToken.equals(tweetActionEvent.authToken)){
            return TweetActions.tweetAction(tweetActionEvent);
        }
        return null;
    }

    @Override
    public Response logOut(LogOutEvent logOutEvent) {
        User.getUserByAuthToken(authToken).lastseennow();
        Main.onlineUsers.remove(authToken);
        return null;
    }

    @Override
    public Response closeApp(CloseAppEvent closeAppEvent) {
        Main.onlineUsers.remove(authToken);
        User.getUserByAuthToken(authToken).lastseennow();
        this.sender.close();
        this.interrupt();
        return null;
    }

    @Override
    public Response getTweet(GetTweetEvent getTweetEvent) {
        if (authToken.equals(getTweetEvent.authToken)){
            return new TweetResponse(
                    User.getUser(getTweetEvent.id).Tweets.get(Integer.parseInt(getTweetEvent.tweetId)-1));
        }
        return null;
    }

    @Override
    public Response timeLineTweets(TimeLineTweetsEvent timeLineTweetsEvent) {
        if (authToken.equals(timeLineTweetsEvent.authToken)){
            LinkedList<Tweet> mainTweets =  TimelineController.timeline(timeLineTweetsEvent.authToken);
            LinkedList<Tweet> tweets = new LinkedList<>();
            LinkedList<byte[]> tweetsPic = new LinkedList<>();
            LinkedList<byte[]> profilePic = new LinkedList<>();
            for (Tweet mainTweet : mainTweets) {
                try {
                    tweets.add(mainTweet);
                    tweetsPic.add(Load.loadTweetPic(mainTweet.tweetId, mainTweet.Id));
                    profilePic.add(Load.picToByte(mainTweet.Id));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new GetTweetsResponse(tweets, tweetsPic, profilePic);
        }
        return null;
    }

    @Override
    public Response getPvs(GetPvsEvent getPvsEvent) {
        if (getPvsEvent.authToken.equals(authToken)){
            try {
                return PvsController.getPvs(authToken);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Response getGroups(GetGroupsEvent getGroupsEvent) {
        if (getGroupsEvent.authToken.equals(authToken)) {
            User user = User.getUserByAuthToken(authToken);
            LinkedList<Integer> sortedGroups = GroupController.sortGroups(authToken);
            LinkedList<GroupChatOverview> groupChats = new LinkedList<>();
            for (Integer sortedGroup : sortedGroups) {
                LinkedList<String> users = new LinkedList<>();
                String shown = "....";
                if (GroupChat.groupChats.get(sortedGroup).messages.size()>0){
                    shown = GroupChat.groupChats.get(sortedGroup).messages.getLast().text;
                }
                String groupId = GroupChat.groupChats.get(sortedGroup).id+"";
                String unseen = GroupChat.groupChats.get(sortedGroup).getUnseen(user.Id);
                String name = GroupChat.groupChats.get(sortedGroup).name;
                for (String s : GroupChat.groupChats.get(sortedGroup).users) {
                    users.add(User.id2username(s));
                }
                groupChats.add(new GroupChatOverview(shown, name, unseen, groupId, users));
            }
            return new GetGroupsResponse(groupChats);
        }
        return null;
    }

    @Override
    public Response getCategories(GetCategoriesEvent getCategoriesEvent) {
        if (getCategoriesEvent.authToken.equals(authToken)){
            LinkedList<Category> categories = new LinkedList<>();
            for (Category category : User.getUserByAuthToken(authToken).categories) {
                categories.add(category);
            }
            for (Category category : categories) {
                for (String person : category.people) {
                    category.people.remove(person);
                    category.people.add(User.id2username(person));
                }
            }
            return new GetCategoriesResponse(categories);
        }
        return null;
    }

    @Override
    public Response getSavedMessage(GetSavedMessageEvent getSavedMessageEvent) {
        if (getSavedMessageEvent.authToken.equals(authToken)){
            try {
                return new GetSavedMessageResponse(User.getUserByAuthToken(authToken).savedMessage,
                        Load.loadSavedMessagePics(authToken));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Response searchPv(SearchForPvEvent searchForPvEvent) {
        if (authToken.equals(searchForPvEvent.authToken)){
            User user = User.getUserByAuthToken(searchForPvEvent.authToken);
            if (!CheckUsername.checkusername(searchForPvEvent.username)){
                return new SearchPvResponse("Username Does Not Exist!", null);
            }
            else if (!(user.followers.contains(User.username2id(searchForPvEvent.username))
                || (user.following.contains(User.username2id(searchForPvEvent.username))))){
                    return new SearchPvResponse("At least one of you should follow the other!", null);
            }
            else{
                pvChat pvChat = null;
                if (CheckUsername.checkusername(searchForPvEvent.username)){
                    try {
                        pvChat = Load.loadPvChat(user.Id+ User.username2id(searchForPvEvent.username));
                        if (pvChat.id.equals("00")){
                            pvChat = Load.loadPvChat(User.username2id(searchForPvEvent.username)+user.Id);
                            if (pvChat.id.equals("00")){
                                pvChat = new pvChat(User.username2id(searchForPvEvent.username), user.Id);
                                try {
                                    Save.savePvChat(pvChat);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return new SearchPvResponse("", pvChat);
                }
            }
        }
        return null;
    }

    @Override
    public Response savedMessageAction(SavedMessageActionEvent savedMessageActionEvent) {
        if (authToken.equals(savedMessageActionEvent.authToken)){
            User user = User.getUserByAuthToken(authToken);
            if (savedMessageActionEvent.action.equals("edit")){
                for (Message message : user.savedMessage.messages) {
                    if (message.id.equals(savedMessageActionEvent.messageId)){
                        message.text = savedMessageActionEvent.message;
                    }
                }
            }
            else if (savedMessageActionEvent.action.equals("delete")){
                Message message1 = null;
                for (Message message : user.savedMessage.messages) {
                    if (message.id.equals(savedMessageActionEvent.messageId)){
                        message1 = message;
                    }
                }
                user.savedMessage.messages.remove(message1);
            }
            else if (savedMessageActionEvent.action.equals("send")){
                if (savedMessageActionEvent.pic != null){
                    Save.saveSavedMessagePic(savedMessageActionEvent.pic, authToken);
                }
                try {
                    user.savedMessage.messages.add(new Message(user.Id, user.Id, savedMessageActionEvent.message));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Response categoryAction(CategoryActionEvent categoryActionEvent) {
        if (categoryActionEvent.authToken.equals(authToken)){
            if (categoryActionEvent.message.equals("")){
                if (!(CheckFollow.checkFollowers(categoryActionEvent.username)
                        || CheckFollow.checkFollowing(categoryActionEvent.username))){
                    return new CategoryActionResponse("You Can't Add this user");
                }
                else if (!CheckUsername.checkusername(categoryActionEvent.username)){
                    return new CategoryActionResponse("User Doesn't Exist");
                }
                else{
                    CategoryController.createOrAddToCategory(categoryActionEvent.categoryName,
                            categoryActionEvent.username, authToken);
                    return new CategoryActionResponse("Category Created!");
                }
            }
            else{
                try {
                    return CategoryController.sendMsg(categoryActionEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Response getPvChat(GetPvChatEvent getPvChatEvent) {
        User user = User.getUserByAuthToken(authToken);
        if (getPvChatEvent.authToken.equals(authToken)){
            pvChat pvChat = null;
            LinkedList<byte[]> pics = new LinkedList<>();
            try {
                 pvChat = Load.loadPvChat(getPvChatEvent.pvId);
                 if (pvChat.id.equals("00")){
                     pvChat = null;
                 }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (pvChat != null){
                for (Message message : pvChat.messages) {
                    message.giver = User.id2username(message.giver);
                    message.reciever = User.id2username(message.reciever);
                }
                if (pvChat.id1.equals(user.Id)){
                    pvChat.unreadid1 = 0;
                }
                else if (pvChat.id2.equals(user.Id)){
                    pvChat.unreadid2 = 0;
                }
                try {
                    pics = Load.loadPvChatPics(authToken, getPvChatEvent.pvId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new GetPvChatResponse(pvChat, pics);
        }
        return null;
    }

    @Override
    public Response pvMessageAction(PvMessageActionEvent pvMessageActionEvent) {
        pvChat pv = null;
        try {
            pv = Load.loadPvChat(pvMessageActionEvent.pvId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (authToken.equals(pvMessageActionEvent.authToken)){
            User user = User.getUserByAuthToken(authToken);
            if (pvMessageActionEvent.action.equals("edit")){
                for (Message message : pv.messages) {
                    if (message.id.equals(pvMessageActionEvent.messageId)){
                        message.text = pvMessageActionEvent.message;
                    }
                }
                try {
                    Save.savePvChat(pv);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (pvMessageActionEvent.action.equals("delete")){
                Message message1 = null;
                for (Message message : pv.messages) {
                    if (message.id.equals(pvMessageActionEvent.messageId)){
                        message1 = message;
                    }
                }
                pv.messages.remove(message1);
                try {
                    Save.savePvChat(pv);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (pvMessageActionEvent.action.equals("send")){
                if (pvMessageActionEvent.pic != null){
                    try {
                        Save.savePvMessagePic(pvMessageActionEvent.pic,pv.id);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (pv.id1.equals(user.Id)){
                        Message message = new Message(pv.id2,user.Id,pvMessageActionEvent.message);
                        message.setId(pv.messages.size()+"");
                        pv.messages.add(message);
                        pv.unreadid2++;
                    }
                    else{
                        Message message = new Message(pv.id1,user.Id,pvMessageActionEvent.message);
                        message.setId(pv.messages.size()+"");
                        pv.messages.add(message);
                        pv.unreadid1++;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Save.savePvChat(pv);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Response createGroup(CreateGroupEvent createGroupEvent) {
        if (createGroupEvent.authToken.equals(authToken)){
            User user = User.getUserByAuthToken(authToken);
            if (!(user.followers.contains(User.username2id(createGroupEvent.username))
                    || user.following.contains(User.username2id(createGroupEvent.username)))){
                return new CreateGroupResponse("You Can't Add this member");
            }
            else if (!CheckUsername.checkusername(createGroupEvent.username)){
                return new CreateGroupResponse("You Can't Add this member");
            }
            else{
                GroupController.createGroup(createGroupEvent.groupName, createGroupEvent.username, authToken);
            }
        }
        return null;
    }

    @Override
    public Response getGroup(GetGroupEvent getGroupEvent) {
        if (getGroupEvent.authToken.equals(authToken)){
            User user = User.getUserByAuthToken(authToken);
            GroupChat groupChat = GroupChat.groupChats.get(Integer.parseInt(getGroupEvent.groupId));
            groupChat.removeUnseen(user.Id);
            for (Message message : groupChat.messages) {
                message.giver = User.id2username(message.giver);
            }
            try {
                return new GetGroupResponse(groupChat, Load.loadGroupPics(authToken, getGroupEvent.groupId));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Response groupMessageActionEvent(GroupMessageActionEvent groupMessageActionEvent) {
        if (authToken.equals(groupMessageActionEvent.authToken)){
            GroupChat groupChat = GroupChat.groupChats.get(Integer.parseInt(groupMessageActionEvent.groupId));
            User user = User.getUserByAuthToken(authToken);
            if (groupMessageActionEvent.action.equals("edit")){
                for (Message message : groupChat.messages) {
                    if (message.id.equals(groupMessageActionEvent.messageId)){
                        message.text = groupMessageActionEvent.message;
                    }
                }
            }
            else if (groupMessageActionEvent.action.equals("delete")){
                Message message1 = null;
                for (Message message : groupChat.messages) {
                    if (message.id.equals(groupMessageActionEvent.messageId)){
                        message1 = message;
                    }
                }
                groupChat.messages.remove(message1);
            }
            else if (groupMessageActionEvent.action.equals("send")){
                if (groupMessageActionEvent.pic != null){
                    try {
                        Save.saveGroupChatPic(groupMessageActionEvent.pic,groupChat.id+"");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Message message = new Message(groupChat.id+"",user.Id,groupMessageActionEvent.message);
                    message.setId(groupChat.messages.size()+"");
                    groupChat.messages.add(message);
                    groupChat.doUnseen(user.Id);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

}
