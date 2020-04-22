package cz.kylberger.dijkstra;

public class Edge {
    private Vertex target;
    private int weight;

    public Edge(Vertex target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    public Vertex getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return target.getId() + ", w: " + weight;
    }
}
