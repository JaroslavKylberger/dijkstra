package cz.kylberger.dijkstra;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class DijkstraTest {

    private static final int[] V = {1, 2, 3, 4, 5};
    private static final int[][] E = {{1, 2, 2}, {2, 3, 1}, {1, 3, 4}, {3, 4, 1}};

    private static final int[][][] PATHS = {
            {{},        {2},    {2, 3}, {2, 3, 4}, null},
            {{1},       {},     {3},    {3, 4},    null},
            {{2, 1},    {2},    {},     {4},       null},
            {{3, 2, 1}, {3, 2}, {3},    {},        null},
            {null,      null,   null,   null,      {}}
    };

    private static Graph graph;

    @BeforeClass
    public static void beforeClass() {
        graph = createGraph(V, E);
    }

    @Test
    public void sourceTargetExistingTest() {
        findShortestPath(graph, 1, 4);
        findShortestPath(graph, 1, 3);
    }

    @Test
    public void sourceOnlyTest() {
        findShortestPath(graph, 1);
        findShortestPath(graph, 2);
        findShortestPath(graph, 3);
        findShortestPath(graph, 4);
    }

    @Test
    public void sourceTargetNoPathTest() {
        findShortestPath(graph, 1, 5);
    }

    @Test
    public void sourceIsTargetTest() {
        findShortestPath(graph, 1, 1);
    }

    private static void findShortestPath(Graph graph, int sourceId, int targetId) {
        Vertex source = graph.getVertex(sourceId);
        Vertex target = graph.getVertex(targetId);
        List<Edge> path = Dijkstra.shortestPath(graph, source, target);
        assertArrayEquals(PATHS[sourceId - 1][targetId - 1], convertPathToIds(path));
        printPath(source, target, path);
    }

    private static void findShortestPath(Graph graph, int sourceId) {
        Vertex source = graph.getVertex(sourceId);
        Map<Vertex, List<Edge>> paths = Dijkstra.shortestPath(graph, source);
        for (Map.Entry<Vertex, List<Edge>> entry : paths.entrySet()) {
            Vertex target = entry.getKey();
            List<Edge> path = entry.getValue();
            assertArrayEquals(PATHS[sourceId - 1][target.getId() - 1], convertPathToIds(path));
            printPath(source, target, path);
        }
    }

    private static Graph createGraph(int[] vertices, int[][] edges) {
        Graph graph = new Graph();
        for (int vId : vertices) {
            Vertex v = new Vertex(vId);
            graph.addVertex(v);
        }
        for (int[] e : edges) {
            Vertex v1 = graph.getVertex(e[0]);
            Vertex v2 = graph.getVertex(e[1]);
            v1.addEdge(v2, e[2]);
            v2.addEdge(v1, e[2]);
        }
        return graph;
    }

    private static int[] convertPathToIds(List<Edge> path) {
        if (path == null)
            return null;
        int[] result = new int[path.size()];
        for (int i = 0; i < path.size(); i++) {
            result[i] = path.get(i).getTarget().getId();
        }
        return result;
    }

    private static void printPath(Vertex source, Vertex target, List<Edge> path) {
        System.out.println("(" + source.getId() + ", " + target.getId() + ")");
        if (path == null) {
            System.out.println("There is no path.");
            return;
        }
        System.out.print("Path: ");
        System.out.print(source.getId());
        for (Edge e : path) {
            System.out.print(" -> " + e.getTarget().getId());
        }
        System.out.println();
        System.out.print("Distance: " + target.getDist() + " (");
        for (Edge e : path) {
            String s = e.getTarget().equals(target) ? String.valueOf(e.getWeight()) : e.getWeight() + " + ";
            System.out.print(s);
        }
        System.out.println(")");
    }

}
