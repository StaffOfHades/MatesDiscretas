import java.util.ArrayList;
import java.util.List;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/28/15 for MatematicasDiscretas
 */
public class Node<E> {

    public E element;
    private List<Node<E>> mChilds;

    public Node(E element) {
        this.element = element;
        mChilds = new ArrayList<Node<E>>();
    }

    public void addChild(Node<E> node) {
        mChilds.add(node);
    }

    public List<Node<E>> getChilds() {
        return mChilds;
    }

}
