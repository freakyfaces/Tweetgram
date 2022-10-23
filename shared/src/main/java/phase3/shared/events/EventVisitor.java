package phase3.shared.events;


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
import phase3.shared.response.Response;

public interface EventVisitor {

    Response signUp(SignUpFormEvent event);

    Response login(LoginFormEvent event);

    Response changePassword(ChangePasswordEvent event);

    Response privacySetting(PrivacySettingEvent event);

    Response profileInfo(ProfileInfoEvent event);

    Response editProfile(EditProfileFormEvent event);

    Response notifications(NotificationsEvent event);

    Response request(RequestEvent event);

    Response getProfile(GetProfileEvent event);

    Response profileAction(ProfilePageActionsEvent event);

    Response getLists(ListsEvent event);

    Response shareTweet(ShareTweetEvent event);

    Response getTweets(GetTweetsEvent event);

    Response tweetAction(TweetActionEvent event);

    Response logOut(LogOutEvent event);

    Response closeApp(CloseAppEvent event);

    Response getTweet(GetTweetEvent event);

    Response timeLineTweets(TimeLineTweetsEvent event);

    Response getPvs(GetPvsEvent event);

    Response getGroups(GetGroupsEvent event);

    Response getCategories(GetCategoriesEvent event);

    Response getSavedMessage(GetSavedMessageEvent event);

    Response searchPv(SearchForPvEvent event);

    Response savedMessageAction(SavedMessageActionEvent event);

    Response categoryAction(CategoryActionEvent categoryActionEvent);

    Response getPvChat(GetPvChatEvent event);

    Response pvMessageAction(PvMessageActionEvent event);

    Response createGroup(CreateGroupEvent event);

    Response getGroup(GetGroupEvent event);

    Response groupMessageActionEvent(GroupMessageActionEvent event);
}
