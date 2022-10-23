package phase3.shared.response.messaging;

import phase3.shared.model.messaging.Category;
import phase3.shared.response.Response;
import phase3.shared.response.ResponseVisitor;

import java.util.LinkedList;

public class GetCategoriesResponse extends Response {
    public LinkedList<Category> categories;

    public GetCategoriesResponse(LinkedList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visit(this);
    }
}
