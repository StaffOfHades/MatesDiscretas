package graphs;

import java.util.Iterator;
import java.util.List;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 11/4/15 for MatematicasDiscretas
 */
public abstract class AbstractFirstSearch<E> {

    private final String TAG;
    protected int time;
    protected String divider;

    protected AbstractFirstSearch(String tag) {
        TAG = tag;
    }

    public void search(GraphView<E> graph, String fileName) {
        System.out.println("\n" + TAG + " Process");
        time = 0;

        if ( graph.isDirected() ) {
            divider = " -> ";
        } else {
            divider = " <-> ";
        }

        graph.resetVertexState();

        Iterator<Vertex<E>> vertexIterator = graph.vertexIterator();
        if (vertexIterator != null) {
            List<Vertex<E>> list = internalSearch(graph, vertexIterator);
            System.out.println("Saving result to " + fileName);
            graph.saveToFile(fileName, TAG, list);
        }
    }

    protected abstract List<Vertex<E>> internalSearch(GraphView<E> graph, Iterator<Vertex<E>> vertexIterator);
}
