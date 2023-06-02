package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<T> {
    private T value;
    private List<Edge<T>> adjacent;

    private int time;

    public Vertex(T value) {
        this.value = value;
        adjacent = new ArrayList<>();
        this.time = 0;
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

    public void setTime(int time) {
    	this.time = time;
    }

    public int getTime() {
    	return this.time;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Vertex{value=").append(value).append(", adjacent=[");
        for (int i = 0; i < adjacent.size(); i++) {
            sb.append(adjacent.get(i).getDestination().getValue());
            if (i < adjacent.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
