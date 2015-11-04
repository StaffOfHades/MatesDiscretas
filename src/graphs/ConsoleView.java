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

    private static final String
            INPUT = "/Users/mauriciog/IdeaProjects/MatematicasDiscretas/src/graphs/Input",
            OUTPUT = "/Users/mauriciog/IdeaProjects/MatematicasDiscretas/src/graphs/Output";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get(INPUT),
                Charset.defaultCharset() );
        System.out.println("Read lines from file " + INPUT);

        boolean directed = false;
        try {
            directed = Boolean.parseBoolean(lines.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        lines.remove(0);

        GraphView<String> view = new GraphView<String>(directed);
        System.out.println("Created " + (directed ? "a " : "an un") + "directed graph");

        DepthFirstSearch<String> depth = new DepthFirstSearch<String>();
        System.out.println("Created a DepthFirstSearch object");

        BreadthFirstSearch<String> breadth = new BreadthFirstSearch<String>();
        System.out.println("Created a BreadthFirstSearch object");

        view.clearFileText(OUTPUT);
        System.out.println("Cleared text for file " + OUTPUT);

        System.out.println("\n" + "Input Text");

        String[] vertexes;
        for (String line : lines) {
            System.out.println(line);
            vertexes = line.split("-");
            if (vertexes.length == 2) {
                view.addVertex( vertexes[0].trim() );
            }
        }

        Vertex<String> from, to;
        for (String line : lines) {
            vertexes = line.split("-");
            if (vertexes.length == 2) {
                from = new Vertex<String>( vertexes[0].trim() );
                to = new Vertex<String>( vertexes[1].trim() );
                view.addEdge(from, to);
            }
        }

        breadth.search(view, OUTPUT);
        depth.search(view, OUTPUT);

    }

}
