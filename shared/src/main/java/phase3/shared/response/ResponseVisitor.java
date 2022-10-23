package phase3.shared.response;


public interface ResponseVisitor<F> {


    void visit(F object);

}
