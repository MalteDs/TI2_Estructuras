package model;

import java.util.List;

public interface Grafo <T> {
    void addVertex(T vertex);
    void addEdge(T source, T destination, int weight);
    void removeVertex(T vertex);
    void removeEdge(T source, T destination);
    List<T> bfs(T start);
    List<T> dfs(T start);
}
