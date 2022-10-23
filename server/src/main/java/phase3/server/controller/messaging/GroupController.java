package phase3.server.controller.messaging;

import phase3.server.controller.authentication.Login;
import phase3.shared.model.messaging.GroupChat;
import phase3.shared.model.messaging.Message;
import phase3.shared.model.User;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class GroupController {
    public static Boolean checkGroup(String name, User user){
        for (Integer group : user.groups) {
            GroupChat groupChat = GroupChat.groupChats.get(group);
            if (groupChat.name.equals(name) && groupChat.getUsers().contains(user.Id)){
                return true;
            }
        }
        return false;
    }
    public static void addMember(int groupId, String username){
        GroupChat groupChat = GroupChat.groupChats.get(groupId);
        groupChat.addUsers(User.username2id(username));
    }
    public void addMessage(String message, int groupId, User user) throws FileNotFoundException {
        GroupChat groupChat = GroupChat.groupChats.get(groupId);
        groupChat.addMessage(new Message(groupId+"", user.Id, message));
    }
    public static void createGroup(String name, String member, String authToken){
        User user = User.getUserByAuthToken(authToken);
        if (!checkGroup(name, user)){
            GroupChat groupChat = new GroupChat(name);
            groupChat.addUsers(user.Id);
            groupChat.addUsers(User.username2id(member));
        }
        else{
            for (Integer group : user.groups) {
                if (GroupChat.groupChats.get(group).getUsers().contains(user.Id) &&
                        GroupChat.groupChats.get(group).name.equals(name)){
                    addMember(group, member);
                }
            }
        }
    }
    public static LinkedList<Integer> sortGroups(String authToken){
        User user = User.getUserByAuthToken(authToken);
        LinkedList<Integer> groups = user.groups;
        LinkedList<Integer> sortedGroups = new LinkedList<>();
        for (Integer group : groups) {
            GroupChat groupChat = GroupChat.groupChats.get(group);
            if (groupChat.unseen.get(groupChat.getUsers().indexOf(user.Id))>0){
                sortedGroups.addFirst(group);
            }
            else {
                sortedGroups.addLast(group);
            }
        }
        return sortedGroups;
    }
}
