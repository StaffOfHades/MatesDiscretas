package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/28/15 for MatematicasDiscretas
 */
public class Vertex<E> {

    public final E value;
    public ColorState state;
    public List<Vertex<E>> children;
    public String result;
    public int discoveryTime, finishTime, distance;
    public Vertex<E> parent;

    public Vertex(E value) {
        this.value = value;
        children = new ArrayList<Vertex<E>>();
        state = ColorState.White;
    }

    public void reset() {
        parent = null;
        children.clear();
        result = "";
        discoveryTime = 0;
        finishTime = 0;
        distance = Integer.MAX_VALUE;
        state = ColorState.White;
    }

    public enum ColorState {
        White, Gray, Black;
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Vertex) ) {
            return false;
        }
        E a = this.value;
        E b = ( (Vertex<E>) object).value;
        a.equals(b);
        return a.equals(b);
    }

}
