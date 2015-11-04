package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/29/15 for MatematicasDiscretas
 */
public class BreadthFirstSearch<E> {

    private static final String TAG = "Breadth First Search";


    private String divider;

    public BreadthFirstSearch() {}

    public void search(GraphView<E> graph, String fileName) {
        System.out.println("\n" + TAG + " Process");

        if ( graph.isDirected() ) {
            divider = " -> ";
        } else {
            divider = " <-> ";
        }

        graph.resetVertexState();

        Iterator<Vertex<E>> vertexIterator = graph.vertexIterator();
        if (vertexIterator != null) {
            Vertex<E> progenitor = vertexIterator.next();
            progenitor.hasParent = false;
            progenitor.depth = 0;
            progenitor.state = Vertex.VisitState.Discovered;
            progenitor.branch = progenitor.value.toString();
            System.out.println("Discovered parent " + progenitor.value);

            Queue<Vertex<E>> queue = new LinkedList<Vertex<E>>();

            queue.add(progenitor);
            System.out.println("Added " + progenitor.value + " to new queue");

            Vertex<E> child;
            Iterator<Vertex<E>> edgeIterator;
            while( !queue.isEmpty() ) {
                Vertex<E> parent = queue.remove();
                System.out.println("Removed " + parent.value + " from queue");
                edgeIterator = graph.edgeIterator(parent);
                if (edgeIterator != null) {
                    while ( edgeIterator.hasNext() ) {
                        child = edgeIterator.next();
                        System.out.println("Connection exists from " + parent.value + " to " + child.value);
                        if (child.state == Vertex.VisitState.Undiscovered) {
                            child.state = Vertex.VisitState.Discovered;
                            System.out.println(child.value + " has been discovered");

                            parent.children.add(child);
                            System.out.println(child.value + " is a new child of " + parent.value);

                            child.depth = parent.depth + 1;
                            System.out.println(child.value  + " has a depth of " + child.depth);

                            child.branch = parent.branch + divider + child.value;
                            System.out.println("Branch for " + child.value  + " is " + child.branch);

                            queue.add(child);
                            System.out.println("Added " + child.value + " to queue");
                        } else {
                            System.out.println(child.value + " already has a been discovered");
                        }
                    }
                }

            }
            System.out.println("Saving result to " + fileName);
            graph.saveToFile(fileName, TAG, progenitor);
        }

    }

}
