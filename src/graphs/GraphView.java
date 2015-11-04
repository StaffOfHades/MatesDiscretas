package graphs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/30/15 for MatematicasDiscretas
 */
public class GraphView<E> {

    private final List<Vertex<E>> mVertexList;
    private final Map<Vertex<E>, List<Vertex<E>>> mVertexListMap;
    private final boolean isDirected;

    public GraphView(boolean isDirected) {
        this.isDirected = isDirected;
        mVertexList = new ArrayList<Vertex<E>>();
        mVertexListMap = new HashMap<Vertex<E>, List<Vertex<E>>>();
    }

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

    public Vertex<E> addVertex(Vertex<E> v) {
        if ( !mVertexList.contains(v) ){
            mVertexList.add(v);
            mVertexListMap.put(v, new ArrayList<Vertex<E>>() );
        } else {
            v = mVertexList.get( mVertexList.indexOf(v) );
        }
        return v;
    }

    public void addEdge(Vertex<E> from, Vertex<E> to) {
        from = addVertex(from);
        to = addVertex(to);

        mVertexListMap.get(from).add(to);

        if (!isDirected) {
            mVertexListMap.get(to).add(from);
        }
    }

    public Iterator<Vertex<E>> vertexIterator() {
        return mVertexList.iterator();
    }

    public Iterator<Vertex<E>> edgeIterator(Vertex<E> from) {
        if (!mVertexListMap.containsKey(from))
            return null;
        return mVertexListMap.get(from).iterator();
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void resetVertexState() {
        for (Vertex<E> vertex : mVertexList) {
            vertex.reset();
        }
    }

    public boolean saveToFile(String fileName, String title, List<Vertex<E>> list) {
        Vertex<E> vertex;
        try {
            File output = new File(fileName);
            FileWriter stream = new FileWriter(output, true);

            stream.write(title);
            System.out.println("\n" + title + " Result");

            List<String> lines ;
            for (Vertex<E> v : list) {
                lines = getResult(v, new ArrayList<String>() );
                for (String line : lines) {
                    stream.write("\n");
                    stream.write(line);
                    System.out.println(line);
                }
            }

            stream.write("\n");
            stream.write("\n");

            stream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearFileText(String fileName) {
        try {
            File output = new File(fileName);
            FileWriter stream = new FileWriter(output, false);
            stream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<String> getResult(Vertex<E> parent, List<String> lines) {
        if (parent.children.size() == 0) {
            lines.add(parent.branch);
            return lines;
        } else {
            for (Vertex<E> child : parent.children) {
                lines = getResult(child, lines);
            }
        }
        return lines;
    }
}
