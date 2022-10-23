package phase3.shared.response.personalPage;

import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ListsResponse extends Response {

    public LinkedList<String> followers;
    public LinkedList<String> followings;
    public LinkedList<String> blackList;
    public LinkedList<byte[]> followersPic;
    public LinkedList<byte[]> followingsPic;
    public LinkedList<byte[]> blackListPic;
    public ListsResponse(LinkedList<String>  followers, LinkedList<String>  followings,
                         LinkedList<String> blackList, LinkedList<byte[]>  followersPic,
                         LinkedList<byte[]>  followingsPic,
                         LinkedList<byte[]> blackListPic) {
        this.followers = followers;
        this.followings = followings;
        this.blackList = blackList;
        this.followersPic = followersPic;
        this.followingsPic = followingsPic;
        this.blackListPic = blackListPic;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
