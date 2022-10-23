package phase3.server.controller.personalPage;

import phase3.server.dataBase.Load;
import phase3.shared.model.User;
import phase3.shared.response.personalPage.ListsResponse;
import java.io.IOException;
import java.util.LinkedList;

public class GetLists {
    public static ListsResponse getLists(String authToken) throws IOException {
        User user = User.getUserByAuthToken(authToken);
        LinkedList<String> followers = new LinkedList<>();
        LinkedList<byte[]> followersPic = new LinkedList<>();
        for (int i = 0; i < user.followers.size(); i++) {
            followers.add(User.id2username(user.followers.get(i)));
            followersPic.add(Load.picToByte(user.followers.get(i)));
        }
        LinkedList<String> followings = new LinkedList<>();
        LinkedList<byte[]> followingsPic = new LinkedList<>();
        for (int i = 0; i < user.following.size(); i++) {
            followings.add(User.id2username(user.following.get(i)));
            followingsPic.add(Load.picToByte(user.following.get(i)));
        }
        LinkedList<String> blackList = new LinkedList<>();
        LinkedList<byte[]> blackListPic = new LinkedList<>();
        for (int i = 0; i < user.blacklist.size(); i++) {
            blackList.add(User.id2username(user.blacklist.get(i)));
            blackListPic.add(Load.picToByte(user.blacklist.get(i)));
        }
        return new ListsResponse(followers, followings, blackList, followersPic, followingsPic, blackListPic);
    }
}
