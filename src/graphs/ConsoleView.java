package graphs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 10/28/15 for MatematicasDiscretas
 */
public class ConsoleView {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("/Users/mauriciog/IdeaProjects/MatematicasDiscretas/src/graphs/Vertexes"),
                Charset.defaultCharset() );
        boolean directed = false;

        try {
            directed = Boolean.parseBoolean(lines.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        lines.remove(0);

        GraphView<String> view = new GraphView<String>(directed);

        System.out.println("Input");

        String[] vertexes;
        for (String line : lines) {
            System.out.println(line);
            vertexes = line.split("-");
            view.addVertex( vertexes[0].trim() );
        }

        System.out.println();

        Vertex<String> from, to;
        for (String line : lines) {
            vertexes = line.split("-");
            from = new Vertex<String>( vertexes[0].trim() );
            to = new Vertex<String>( vertexes[1].trim() );
            view.addEdge(from, to);
        }

        DepthFirstSearch<String> depth = new DepthFirstSearch<String>();

        depth.search(view);

        view.saveToFile("/Users/mauriciog/IdeaProjects/MatematicasDiscretas/src/graphs/Output", "Depth First Search");

        System.out.println("Result" + view.result);

        BreadthFirstSearch<String> breathd = new BreadthFirstSearch<String>();

        breathd.search(view);
    }

}
