import java.util.Iterator;

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
        Vertex<E> v = vertexIterator.next();
        //System.out.println("Parent Vertex has value " + v.value);
    }

    private void visit(GraphView<E> graph, Vertex<E> from) {
        Iterator<Vertex<E>> childIterator = graph.edgeIterator(from);
        Vertex<E> to;
        if (childIterator != null) {
            while (childIterator.hasNext()) {
                to = childIterator.next();
                if (to.state == Vertex.ColorState.White) {
                    to.parent = from;
                    to.state = Vertex.ColorState.Gray;
                }
            }
        }
        from.state = Vertex.ColorState.Black;

        childIterator = graph.edgeIterator(from);
        if (childIterator != null) {
            while (childIterator.hasNext()) {
                to = childIterator.next();
                if (to.state == Vertex.ColorState.Gray) {
                    visit(graph, from);
                }
            }
        }
    }

}
