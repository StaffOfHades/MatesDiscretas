package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/29/15 for MatematicasDiscretas
 */
public class BreadthFirstSearch<E> {

    protected int time;

    public BreadthFirstSearch() {}

    public void search(GraphView<E> graph) {
        time = 0;
        graph.resetVertexState();

        Iterator<Vertex<E>> vertexIterator = graph.vertexIterator();
        if (vertexIterator != null) {
            Vertex<E> parent = vertexIterator.next();
            parent.distance = 0;
            parent.result = parent.value.toString();
            System.out.println("Visiting parent " + parent.value);
            visit(graph, parent);
        }

    }

    private void visit(GraphView<E> graph, Vertex<E> from) {
        System.out.println("Creating new queque");
        Queue<Vertex<E>> queue = new LinkedList<Vertex<E>>();

        from.state = Vertex.ColorState.Gray;
        queue.add(from);

        System.out.println("Adding " + from.value + " to new queque");

        Vertex<E> child;
        Iterator<Vertex<E>> edgeIterator;
        while( !queue.isEmpty() ) {
            Vertex<E> parent = queue.remove();
            System.out.println("Removed " + parent.value + " from queque");
            edgeIterator = graph.edgeIterator(parent);
            if (edgeIterator != null) {
                while ( edgeIterator.hasNext() ) {
                    child = edgeIterator.next();
                    System.out.println("Found connection to  " + child.value);
                    if (child.state == Vertex.ColorState.White) {
                        parent.children.add(child);
                        child.parent = parent;
                        System.out.println(child.value + " is new child of " + parent.value);

                        child.distance = parent.distance + 1;
                        System.out.println(child.value  + " has a depth of " + child.distance);

                        child.result = parent.result + " - " + child.value;
                        System.out.println("Result for " + child.value  + " is " + child.result);

                        child.state = Vertex.ColorState.Gray;
                        System.out.println("Adding " + child.value + " to queque");
                        queue.add(child);
                    } else {
                        System.out.println(child.value + " already has a been explored");
                    }
                }
            }

        }
    }

}
