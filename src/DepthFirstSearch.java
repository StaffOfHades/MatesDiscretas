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
            Vertex<E> v;
            while (vertexIterator.hasNext()) {
                v = vertexIterator.next();
                if (v.state == Vertex.ColorState.White) {
                    graph.result += "\n" + v.value;
                    visit(graph, v);
                }
            }
        }
    }

    private void visit(GraphView<E> graph, Vertex<E> from) {
        from.state = Vertex.ColorState.Gray;
        time++;
        from.discoveryTime = time;

        //System.out.println("Vertex " + from.value + " discovered at time " + time);

        //discover(graph, v);

        Iterator<Vertex<E>> edgeIterator = graph.edgeIterator(from);
        if (edgeIterator != null) {
            Vertex<E> to;
            while (edgeIterator.hasNext()) {
                to = edgeIterator.next();
                if (to.state == Vertex.ColorState.White) {
                    to.parent = from;
                    graph.result += " - " + to.value;
                    //System.out.println("Vertex " + from.value + " is parent of vertex " + to.value);
                    visit(graph, to);
                }
            }
        }

        from.state = Vertex.ColorState.Black;
        time++;
        from.finishTime = time;

        //System.out.println("Vertex " + from.value + " finised exploring at time " + time);
        //finish(graph, v);
    }

    //protected abstract void finish(GraphView graph, Vertex v);
    //protected abstract void discover(GraphView graph, Vertex v);

}
