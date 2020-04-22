package cz.kylberger.dijkstra;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Vertex {
    private int id;
    private int dist = Integer.MAX_VALUE;
    private Vertex prev;
    private Map<Integer, Edge> edges = new HashMap<>();

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    public Collection<Edge> getEdges() {
        return edges.values();
    }

    public void addEdge(Vertex v, int weight) {
        Edge e = new Edge(v, weight);
        edges.put(e.getTarget().getId(), e);
    }

    public Edge getEdge(Vertex v) {
        return edges.get(v.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
