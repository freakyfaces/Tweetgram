package phase3.server.controller.messaging;

import phase3.server.controller.authentication.Login;
import phase3.server.dataBase.Load;
import phase3.server.dataBase.Save;
import phase3.server.validation.CheckFollow;
import phase3.shared.events.messaging.CategoryActionEvent;
import phase3.shared.model.messaging.Category;
import phase3.shared.model.User;
import phase3.shared.model.messaging.pvChat;
import phase3.shared.response.messaging.CategoryActionResponse;
import java.io.IOException;


public class CategoryController {
    public static Boolean checkCategory(String name, User user){
        for (Category category : user.categories) {
            if (category.name.equals(name)){
                return true;
            }
        }
        return false;
    }
    public static void createOrAddToCategory(String name, String member, String authToken){
        User user = User.getUserByAuthToken(authToken);
        if (!checkCategory(name, user)){
            Category category = new Category(name);
            category.addUser(User.username2id(member));
            user.categories.add(category);
        }
        else{
            for (Category category : user.categories) {
                if (category.name.equals(name) && !category.people.contains(User.username2id(member))){
                    category.addUser(User.username2id(member));
                }
            }
        }
    }
    public static Category getCategory(String name, String authToken){
        for (Category category : User.getUserByAuthToken(authToken).categories) {
            if (category.name.equals(name)){
                return category;
            }
        }
        return Login.user.categories.getLast();
    }
    public static CategoryActionResponse sendMsg(CategoryActionEvent event) throws IOException {
        User user = User.getUserByAuthToken(event.authToken);
        if (checkCategory(event.categoryName, user)){
            Category category1 = getCategory(event.categoryName, event.authToken);
            for (String person : category1.people) {
                if (user.pvs.contains(person+user.Id)){
                    pvChat pvChat = Load.loadPvChat(person+user.Id);
                    pvChat.sendMsg(event.message, user.Id);
                    Save.savePvChat(pvChat);
                }
                else if (user.pvs.contains(user.Id + person)){
                    pvChat pvChat = Load.loadPvChat(user.Id + person);
                    pvChat.sendMsg(event.message, user.Id);
                    Save.savePvChat(pvChat);
                }
                else {
                    if (CheckFollow.checkFollowing(User.id2username(person))
                            || CheckFollow.checkFollowers(User.id2username(person))){
                        pvChat pvChat = new pvChat(person, user.Id);
                        pvChat.sendMsg(event.message, user.Id);
                        Save.savePvChat(pvChat);
                    }
                }
            }
            return new CategoryActionResponse("Message Sent!");
        }
        else{
            return new CategoryActionResponse("Category Doesn't Exist!");
        }
    }
}

