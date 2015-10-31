import java.util.Iterator;

public interface Graph<E> {

    Vertex<E> addVertex(E value);

    Vertex<E> addVertex(int index, E value);

    Vertex<E> addVertex(Vertex<E> v);

    Vertex<E> getVertex(int index);

    void addEdge(Vertex<E> from, Vertex<E> to);

    void addEdge(int indexFrom, int indexTo);

    Iterator<Vertex<E>> vertexIterator();

    Iterator<Vertex<E>> edgeIterator(Vertex<E> from);

    Iterator<Vertex<E>> edgeIterator(int indexFrom);

    int getVertexCount();

    int getCardEdgesCount();

    boolean isDirected();
}
