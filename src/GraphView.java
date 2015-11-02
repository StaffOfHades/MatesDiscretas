import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/30/15 for MatematicasDiscretas
 */
public class GraphView<E> implements Graph<E> {

    private final List<Vertex<E>> mVertexList;
    private final Map<Vertex<E>, List<Vertex<E>>> mVertexListMap;
    private final boolean isDirected;
    public String result;

    public GraphView(boolean isDirected) {
        this.isDirected = isDirected;
        mVertexList = new ArrayList<Vertex<E>>();
        mVertexListMap = new HashMap<Vertex<E>, List<Vertex<E>>>();
        result = "";
    }

    @Override
    public Vertex<E> addVertex(E value) {
        Vertex<E> v = new Vertex<E>(value);
        if ( !mVertexList.contains(v) ) {
            mVertexList.add(v);
            mVertexListMap.put(v, new ArrayList<Vertex<E>>() );
        } else {
            v = mVertexList.get( mVertexList.indexOf(v) );
        }
        return v;
    }

    @Override
    public Vertex<E> addVertex(int index, E value) {
        Vertex v = new Vertex<E>(value);
        if ( !mVertexList.contains(v) ) {
            mVertexList.add(index, v);
            mVertexListMap.put(v, new ArrayList<Vertex<E>>() );
        } else if (mVertexList.indexOf(v) == index) {
            v = mVertexList.get(index);
        } else {
            v = null;
        }

        return v;
    }

    @Override
    public Vertex<E> addVertex(Vertex<E> v) {
        if ( !mVertexList.contains(v) ){
            mVertexList.add(v);
            mVertexListMap.put(v, new ArrayList<Vertex<E>>() );
        } else {
            v = mVertexList.get( mVertexList.indexOf(v) );
        }
        return v;
    }

    @Override
    public Vertex<E> getVertex(int index) {
        if (index >= mVertexList.size() )
            return null;
        return mVertexList.get(index);
    }

    @Override
    public void addEdge(Vertex<E> from, Vertex<E> to) {
        from = addVertex(from);
        to = addVertex(to);

        mVertexListMap.get(from).add(to);

        if (!isDirected) {
            mVertexListMap.get(to).add(from);
        }
    }

    @Override
    public void addEdge(int indexFrom, int indexTo) {
        if (indexFrom >= mVertexList.size() || indexTo >= mVertexList.size())
            return;
        Vertex<E> from = mVertexList.get(indexFrom);
        Vertex<E> to = mVertexList.get(indexTo);
        mVertexListMap.get(from).add(to);
        if (!isDirected) {
            mVertexListMap.get(to).add(from);
        }
    }


    @Override
    public Iterator<Vertex<E>> vertexIterator() {
        return mVertexList.iterator();
    }

    @Override
    public Iterator<Vertex<E>> edgeIterator(Vertex<E> from) {
        if (!mVertexListMap.containsKey(from))
            return null;
        return mVertexListMap.get(from).iterator();
    }

    @Override
    public Iterator<Vertex<E>> edgeIterator(int indexFrom) {
        if ( indexFrom > mVertexList.size() )
            return null;
        Vertex<E> v = mVertexList.get(indexFrom);
        if (!mVertexListMap.containsKey(v))
            return null;
        return mVertexListMap.get(v).iterator();
    }

    @Override
    public int getVertexCount() {
        return mVertexList.size();
    }

    @Override
    public int getCardEdgesCount() {
        int size = 0;
        for ( List<Vertex<E>> list : mVertexListMap.values() )
            size += list.size();
        return size;
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    public void resetVertexState() {
        for (Vertex<E> vertex : mVertexList) {
            vertex.reset();
        }
        result = "";
    }

    public boolean saveToFile(String fileName, String type) {
        try {
            File output = new File(fileName);
            FileWriter fooStream = new FileWriter(output, false);
            fooStream.write(type + result);
            fooStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
