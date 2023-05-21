package model;

import java.util.*;

public class WeightedDirectedGraph<T> implements Grafo<T> {

    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
    private Map<Vertex<T>, List<Edge<T>>> adjacencyList;

    public WeightedDirectedGraph() {
        adjacencyList = new HashMap<>();
    }

    public List<Edge<T>> getAdjacencyListVertex(Vertex<T> vertex) {
        return vertex.getAdjacent();
    }

    public Map<Vertex<T>, List<Edge<T>>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<Edge<T>> getAdjacencyListEdges(Vertex<T> vertex) {
        return vertex.getAdjacent();
    }

    @Override
    public void addVertex(T data) {
        Vertex<T> vertex = new Vertex<>(data);
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(T sourceData, T destData, int weight) {
        Vertex<T> source = getVertex(sourceData);
        Vertex<T> dest = getVertex(destData);

        if (source != null && dest != null) {
            Edge<T> edge = new Edge<>(dest, weight);
            source.addEdge(edge);
        }
    }

    @Override
    public void removeVertex(T data) {
        Vertex<T> vertexToRemove = new Vertex<>(data);
        List<Edge<T>> edgesToRemove = adjacencyList.remove(vertexToRemove);

        for (List<Edge<T>> edges : adjacencyList.values()) {
            edges.removeIf(edge -> edge.getDestination().equals(vertexToRemove));
        }
    }

    @Override
    public void removeEdge(T sourceData, T destData) {
        Vertex<T> source = getVertex(sourceData);
        Vertex<T> dest = getVertex(destData);

        List<Edge<T>> sourceEdges = source.getAdjacent();
        sourceEdges.removeIf(edge -> edge.getDestination().equals(dest));
    }

    @Override
    public List<T> bfs(T startVertex) {
        List<T> result = new ArrayList<>();
        Map<Vertex<T>, Integer> colors = new HashMap<>();
        for (Vertex<T> vertex : adjacencyList.keySet()) {
            colors.put(vertex, WHITE);
        }

        Vertex<T> source = getVertex(startVertex);
        colors.put(source, GRAY);

        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();
            result.add(currentVertex.getValue());

            List<Edge<T>> edges = currentVertex.getAdjacent();
            for (Edge<T> edge : edges) {
                Vertex<T> neighbor = edge.getDestination();
                if (colors.get(neighbor) == WHITE) {
                    colors.put(neighbor, GRAY);
                    queue.add(neighbor);
                }
            }

            colors.put(currentVertex, BLACK);
        }

        return result;
    }

    @Override
    public List<T> dfs(T startVertex) {
        List<T> result = new ArrayList<>();
        Map<Vertex<T>, Integer> colors = new HashMap<>();
        for (Vertex<T> vertex : adjacencyList.keySet()) {
            colors.put(vertex, WHITE);
        }

        Vertex<T> source = getVertex(startVertex);
        dfsVisit(source, colors, result);

        return result;
    }


    public Vertex<T> getVertex(T data) {
        for (Vertex<T> vertex : adjacencyList.keySet()) {
            if (vertex.getValue().equals(data)) {
                return vertex;
            }
        }
        return null;
    }

    private void dfsVisit(Vertex<T> vertex, Map<Vertex<T>, Integer> colors, List<T> result) {
        colors.put(vertex, GRAY);
        result.add(vertex.getValue());

        List<Edge<T>> edges = vertex.getAdjacent();
        for (Edge<T> edge : edges) {
            Vertex<T> neighbor = edge.getDestination();
            if (colors.get(neighbor) == WHITE) {
                dfsVisit(neighbor, colors, result);
            }
        }

        colors.put(vertex, BLACK);
    }
}
