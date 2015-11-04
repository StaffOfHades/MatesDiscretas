package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/28/15 for MatematicasDiscretas
 */
public class Vertex<E> {

    public final E value;
    public VisitState state;
    public List<Vertex<E>> children;
    public String branch;
    public int discoveryTime, finishTime, depth;
    public boolean hasParent;

    public Vertex(E value) {
        this.value = value;
        children = new ArrayList<Vertex<E>>();
    }

    public void reset() {
        hasParent = true;
        children.clear();
        branch = "";
        discoveryTime = 0;
        finishTime = 0;
        depth = 0;
        state = VisitState.Undiscovered;
    }

    public enum VisitState {
        Undiscovered, Discovered, Explored;
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
