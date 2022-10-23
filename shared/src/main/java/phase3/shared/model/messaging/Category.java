package phase3.shared.model.messaging;

import phase3.shared.model.User;

import java.util.LinkedList;

public class Category {
    public LinkedList<String> people;
    public String name;
    public Category(String name){
        this.name = name;
        people = new LinkedList<>();
    }
    public void addUser(String id){
        this.people.add(id);
    }
    public static Category getCategory(String name, User user){
        for (Category category : user.categories) {
            if (category.name.equals(name)){
                return category;
            }
        }
        return new Category("none");
    }
}
