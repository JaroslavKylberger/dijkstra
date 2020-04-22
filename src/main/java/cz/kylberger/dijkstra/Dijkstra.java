package cz.kylberger.dijkstra;

import java.util.*;

public class Dijkstra {

    public static Map<Vertex, List<Edge>> shortestPath(Graph graph, Vertex source) {
        return shortestPathInternal(graph, source, null);
    }

    public static List<Edge> shortestPath(Graph graph, Vertex source, Vertex target) {
        Map<Vertex, List<Edge>> map = shortestPathInternal(graph, source, target);
        return map.get(target);
    }

    private static Map<Vertex, List<Edge>> shortestPathInternal(Graph graph, Vertex source, Vertex target) {
        Map<Vertex, List<Edge>> result = new HashMap<>();
        List<Vertex> vertices = new ArrayList<>();
        for (Vertex v : graph.getVertices()) {
            v.setPrev(null);
            v.setDist(v.equals(source) ? 0 : Integer.MAX_VALUE);
            vertices.add(v);
        }
        while (!vertices.isEmpty()) {
            Vertex u = extractMin(vertices);
            if (u.getPrev() == null && !source.equals(u)) { // there is no path to this and any remaining vertices
                break;
            }
            result.put(u, getPath(source, u));
            if (u.equals(target)) { // shortest path to target found
                break;
            }
            Collection<Edge> edges = u.getEdges();
            for (Edge e : edges) {
                Vertex neighbor = e.getTarget();
                int newDist = u.getDist() + e.getWeight();
                if (newDist < neighbor.getDist()) {
                    neighbor.setPrev(u);
                    neighbor.setDist(newDist);
                }
            }
        }
        return result;
    }

    private static Vertex extractMin(List<Vertex> vertices) {
        Vertex min = vertices.get(0);
        for (Vertex v : vertices) {
            if (v.getDist() < min.getDist()) {
                min = v;
            }
        }
        vertices.remove(min);
        return min;
    }


    private static List<Edge> getPath(Vertex source, Vertex target) {
        if (target.getPrev() == null && !source.equals(target)) {
            return null;
        }
        List<Edge> path = new ArrayList<>();
        Vertex v = target;
        while (v != null) {
            Vertex prev = v.getPrev();
            if (prev != null) {
                path.add(0, prev.getEdge(v));
            }
            v = prev;
        }
        return path;
    }

}
