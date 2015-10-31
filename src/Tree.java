/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/28/15 for MatematicasDiscretas
 */
public class Tree<E> {

    private final Node<E> parent;

    public Tree(Vertex<E> parent) {
        this.parent = new Node<E>(parent.value);
    }

    public void addNode(Vertex<E> vertex) {
        addChild(vertex, parent);
    }

    private void addChild(Vertex<E> child, Node<E> parent) {
        if ( child.parent.value.equals(parent.element) ) {
            parent.addChild( new Node<E>(child.value) );
        }
        for ( Node<E> children : parent.getChilds() ) {
            addChild(child, children);
        }
    }

    public void printTree() {
        System.out.println(parent.element);
        printBranch(parent);
    }

    private void printBranch(Node<E> parent) {
        for ( Node<E> child : parent.getChilds() ) {
            System.out.print(child.element + " ");
        }
        System.out.println();
        printBranch(parent);
    }

}
