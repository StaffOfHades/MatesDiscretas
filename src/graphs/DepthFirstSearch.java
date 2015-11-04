package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/29/15 for MatematicasDiscretas
 */
public class DepthFirstSearch<E> extends AbstractFirstSearch<E> {

    private final static String TAG = "Depth First Search";

    public DepthFirstSearch() {
        super(TAG);
    }

    @Override
    protected List<Vertex<E>> internalSearch(GraphView<E> graph, Iterator<Vertex<E>> iterator) {
        List<Vertex<E>> list = new ArrayList<Vertex<E>>();
        Vertex<E> from;
        while (iterator.hasNext()) {
            from = iterator.next();
            if (from.state == Vertex.VisitState.Undiscovered) {
                list.add(from);
                from.branch = from.value.toString();
                System.out.println("Visiting new parent " + from.value);
                visit(graph, from);
            } else {
                System.out.println(from.value + " has already been discovered");
            }
        }
        return list;
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
