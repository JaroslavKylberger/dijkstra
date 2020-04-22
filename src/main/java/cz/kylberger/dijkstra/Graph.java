package cz.kylberger.dijkstra;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<Integer, Vertex> vertices = new HashMap<>();

    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
    }

    public Vertex getVertex(int id) {
        return vertices.get(id);
    }

}
