package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<T> {
    private T value;
    private List<Edge<T>> adjacent;

    public Vertex(T value) {
        this.value = value;
        adjacent = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public List<Edge<T>> getAdjacent() {
        return adjacent;
    }

    public void addEdge(Edge<T> edge) {
        adjacent.add(edge);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vertex<?> other = (Vertex<?>) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    @Override
    public String toString() {
        return "Vertex{" + "value=" + value + ", adjacent=" + adjacent + '}';
    }
}
