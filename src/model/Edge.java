package model;


import java.util.Objects;

public class Edge<T> {
    private Vertex<T> destination;
    private int weight;

    public Edge(Vertex<T> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Edge)) {
            return false;
        }

        Edge<?> other = (Edge<?>) obj;
        return destination.equals(other.getDestination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination.getValue(), weight);
    }
    @Override
    public String toString() {
        return "Edge {" + "destination=" + destination + ", weight=" + weight + '}';
    }
}

