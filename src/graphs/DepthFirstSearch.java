package graphs;

import java.util.Iterator;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/29/15 for MatematicasDiscretas
 */
public class DepthFirstSearch<E> {

    private final static String TAG = "Depth First Search";

    private int time;
    private String divider;

    public DepthFirstSearch() {}

    public void search(GraphView<E> graph, String fileName) {
        System.out.println("\n" + TAG + " Process");

        if ( graph.isDirected() ) {
            divider = " -> ";
        } else {
            divider = " <-> ";
        }

        time = 0;

        graph.resetVertexState();
        Iterator<Vertex<E>> vertexIterator = graph.vertexIterator();
        if (vertexIterator != null) {
            Vertex<E> from;
            while (vertexIterator.hasNext()) {
                from = vertexIterator.next();
                if (from.state == Vertex.VisitState.Undiscovered) {
                    from.branch = from.value.toString();
                    from.hasParent = false;
                    System.out.println("Visiting new parent " + from.value);
                    visit(graph, from);
                } else {
                    System.out.println(from.value + " has already been discovered");
                }
            }
            System.out.println("Saving result to " + fileName);
            graph.saveToFile( fileName, TAG, graph.vertexIterator() );
        }
    }

    private void visit(GraphView<E> graph, Vertex<E> from) {
        from.state = Vertex.VisitState.Discovered;
        from.discoveryTime = time;
        time++;

        System.out.println("Discovered " + from.value + " at time " + from.discoveryTime);

        //discover(graph, v);

        Iterator<Vertex<E>> edgeIterator = graph.edgeIterator(from);
        if (edgeIterator != null) {
            Vertex<E> to;
            while (edgeIterator.hasNext()) {
                to = edgeIterator.next();
                System.out.println("Connection exist from " + from.value + " to " + to.value);
                if (to.state == Vertex.VisitState.Undiscovered) {
                    from.children.add(to);
                    System.out.println(from.value + " is now connected to " + to.value);

                    to.branch = from.branch + divider + to.value;
                    System.out.println("Branch for " + to.value  + " is " + to.branch);

                    System.out.println("Visiting " + to.value);
                    visit(graph, to);
                } else {
                    System.out.println(to.value + " has already been discovered");
                }
            }
        }

        from.state = Vertex.VisitState.Explored;
        from.finishTime = time;
        time++;

        System.out.println("Finished exploring " + from.value + " at time " + from.finishTime);
        //finish(graph, v);
    }

    //protected abstract void finish(graphs.GraphView graph, graphs.Vertex v);
    //protected abstract void discover(graphs.GraphView graph, graphs.Vertex v);

}
