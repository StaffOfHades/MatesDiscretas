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
            parent.hasParent = false;
            parent.depth = 0;
            parent.branch = parent.value.toString();
            System.out.println("Visiting parent " + parent.value);
            visit(graph, parent);
        }

    }

    private void visit(GraphView<E> graph, Vertex<E> from) {

        System.out.println("Creating new queue");
        Queue<Vertex<E>> queue = new LinkedList<Vertex<E>>();

        from.state = Vertex.VisitState.Discovered;
        queue.add(from);

        System.out.println("Adding " + from.value + " to new queue");

        Vertex<E> child;
        Iterator<Vertex<E>> edgeIterator;
        while( !queue.isEmpty() ) {
            Vertex<E> parent = queue.remove();
            System.out.println("Removed " + parent.value + " from queue");
            edgeIterator = graph.edgeIterator(parent);
            if (edgeIterator != null) {
                while ( edgeIterator.hasNext() ) {
                    child = edgeIterator.next();
                    System.out.println("Connection exist from " + parent.value + " to " + child.value);
                    if (child.state == Vertex.VisitState.Undiscovered) {
                        child.state = Vertex.VisitState.Discovered;
                        System.out.println(child.value + " has been discovered");


                        parent.children.add(child);
                        System.out.println(child.value + " is new child of " + parent.value);

                        child.depth = parent.depth + 1;
                        System.out.println(child.value  + " has a depth of " + child.depth);

                        child.branch = parent.branch + " - " + child.value;
                        System.out.println("Branch for " + child.value  + " is " + child.branch);

                        System.out.println("Adding " + child.value + " to queue");
                        queue.add(child);
                    } else {
                        System.out.println(child.value + " already has a been discovered");
                    }
                }
            }

        }
    }

}
