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


    }

}
