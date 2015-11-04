package graphs;

import java.util.Iterator;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/29/15 for MatematicasDiscretas
 */
public class DepthFirstSearch<E> {

    protected int time;

    public DepthFirstSearch() {}

    public void search(GraphView<E> graph) {
        time = 0;

        graph.resetVertexState();
        Iterator<Vertex<E>> vertexIterator = graph.vertexIterator();
        if (vertexIterator != null) {
            Vertex<E> from;
            while (vertexIterator.hasNext()) {
                from = vertexIterator.next();
                System.out.println("Found node " + from.value);
                if (from.state == Vertex.ColorState.White) {
                    from.result = from.value.toString();
                    System.out.println("Visiting undiscovered node " + from.value);
                    visit(graph, from);
                } else {
                    System.out.println("Node " + from.value + " has already been discovered");
                }
            }
        }
    }

    private void visit(GraphView<E> graph, Vertex<E> from) {
        from.state = Vertex.ColorState.Gray;
        from.discoveryTime = time;
        time++;

        System.out.println("Found " + from.value + " at time " + from.discoveryTime);

        //discover(graph, v);

        Iterator<Vertex<E>> edgeIterator = graph.edgeIterator(from);
        if (edgeIterator != null) {
            Vertex<E> to;
            while (edgeIterator.hasNext()) {
                to = edgeIterator.next();
                System.out.println("Found connection to " + to.value);
                if (to.state == Vertex.ColorState.White) {
                    System.out.println(to.value  + " has new connection from " + from.value);
                    to.parent = from;
                    from.children.add(to);
                    to.result = from.result + " - " + to.value;
                    System.out.println("Visiting " + to.value);
                    visit(graph, to);
                } else {
                    System.out.println(to.value + " has already been discovered");
                }
            }
        }

        from.state = Vertex.ColorState.Black;
        from.finishTime = time;
        time++;

        System.out.println("Finished exploring node " + from.value + " at time " + from.finishTime);
        //finish(graph, v);
    }

    //protected abstract void finish(graphs.GraphView graph, graphs.Vertex v);
    //protected abstract void discover(graphs.GraphView graph, graphs.Vertex v);

}
