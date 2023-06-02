package model;

import java.util.*;

public class WeightedDirectedGraphMatrix<T> implements Grafo<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_WEIGHT = 0;

    private Vertex<T>[] vertices;
    private int[][] adjacencyMatrix;
    private int vertexCount;

    public WeightedDirectedGraphMatrix() {
        this(DEFAULT_CAPACITY);
    }

    public WeightedDirectedGraphMatrix(int capacity) {
        vertices = (Vertex<T>[]) new Vertex[capacity];
        adjacencyMatrix = new int[capacity][capacity];
        vertexCount = 0;
    }

    @Override
    public void addVertex(T data) {
        if (vertexCount >= vertices.length) {
            expandCapacity();
        }
        vertices[vertexCount] = new Vertex<>(data);
        vertexCount++;
    }

    @Override
    public void addEdge(T sourceData, T destData, int weight) {
        int sourceIndex = getIndex(sourceData);
        int destIndex = getIndex(destData);
//        System.out.println(sourceIndex);
//        System.out.println(destIndex);
        if (sourceIndex != -1 && destIndex != -1) {
            adjacencyMatrix[sourceIndex][destIndex] = weight;
            Vertex<T> sourceVertex = vertices[sourceIndex];
            Vertex<T> destVertex = vertices[destIndex];
            Edge<T> edge = new Edge<>(destVertex, weight);
            sourceVertex.addEdge(edge);

            //Por si implementamos ida y vuelta, bidireccional
//            edge = new Edge<>(sourceVertex, weight);
//            destVertex.addEdge(edge);
        } else {
            throw new IllegalArgumentException("One or both vertices do not exist in the graph.");
        }
    }

    @Override
    public void removeVertex(T data) {
        int index = getIndex(data);
        if (index != -1) {

            for (int i = index; i < vertexCount - 1; i++) {
                vertices[i] = vertices[i + 1];
            }
            vertices[vertexCount - 1] = null;

            for (int i = index; i < vertexCount - 1; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    adjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j];
                }
            }
            for (int i = 0; i < vertexCount - 1; i++) {
                for (int j = index; j < vertexCount - 1; j++) {
                    adjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
                }
            }

            vertexCount--;
        }
    }

    @Override
    public void removeEdge(T sourceData, T destData) {
        int sourceIndex = getIndex(sourceData);
        int destIndex = getIndex(destData);
        if (sourceIndex != -1 && destIndex != -1) {
            adjacencyMatrix[sourceIndex][destIndex] = DEFAULT_WEIGHT;
        }
    }

    @Override
    public List<T> bfs(T startVertex) {
        List<T> result = new ArrayList<>();
        boolean[] visited = new boolean[vertexCount];
        Queue<Integer> queue = new LinkedList<>();

        int startIndex = getIndex(startVertex);
        if (startIndex != -1) {
            visited[startIndex] = true;
            queue.add(startIndex);

            while (!queue.isEmpty()) {
                int vertexIndex = queue.poll();
                result.add(vertices[vertexIndex].getValue());

                for (int i = 0; i < vertexCount; i++) {
                    if (adjacencyMatrix[vertexIndex][i] != DEFAULT_WEIGHT && !visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public List<T> dfs(T startVertex) {
        List<T> result = new ArrayList<>();
        boolean[] visited = new boolean[vertexCount];
        Stack<Integer> stack = new Stack<>();

        int startIndex = getIndex(startVertex);
        if (startIndex != -1) {
            visited[startIndex] = true;
            stack.push(startIndex);

            while (!stack.isEmpty()) {
                int vertexIndex = stack.pop();
                result.add(vertices[vertexIndex].getValue());

                for (int i = 0; i < vertexCount; i++) {
                    if (adjacencyMatrix[vertexIndex][i] != DEFAULT_WEIGHT && !visited[i]) {
                        visited[i] = true;
                        stack.push(i);
                    }
                }
            }
        }

        return result;
    }

    public List<T> getAdjacencyList() {
        List<T> adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            adjacencyList.add(vertices[i].getValue());
        }
        return adjacencyList;
    }



    public List<Edge<T>> getAdjacencyListVertex(Vertex<T> vertex) {
        int vertexIndex = getIndex(vertex.getValue());
        if (vertexIndex != -1) {
            List<Edge<T>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertexCount; i++) {
                if (adjacencyMatrix[vertexIndex][i] != DEFAULT_WEIGHT) {
                    Vertex<T> destination = vertices[i];
                    int weight = adjacencyMatrix[vertexIndex][i];
                    Edge<T> edge = new Edge<>(destination, weight);
                    adjacencyList.add(edge);
                }
            }
            return adjacencyList;
        }
        return null;
    }



    public List<Edge<T>> getAdjacencyListEdges(Vertex<T> vertex) {
        int vertexIndex = getIndex(vertex.getValue());
        if (vertexIndex != -1) {
            List<Edge<T>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertexCount; i++) {
                if (adjacencyMatrix[vertexIndex][i] != DEFAULT_WEIGHT) {
                    Vertex<T> destination = vertices[i];
                    int weight = adjacencyMatrix[vertexIndex][i];
                    Edge<T> edge = new Edge<>(destination, weight);
                    adjacencyList.add(edge);
                }
            }
            return adjacencyList;
        }
        return null;
    }

    public Vertex<T> getVertex(T data) {
        for (int i = 0; i < vertexCount; i++) {
            if (vertices[i].getValue().equals(data)) {
                return vertices[i];
            }
        }
        return null;
    }


    private int getIndex(T data) {
        for (int i = 0; i < vertexCount; i++) {
            if (vertices[i].getValue().equals(data)) {
                return i;
            }
        }
        return -1;
    }

    private void expandCapacity() {
        int newCapacity = vertices.length * 2;
        Vertex<T>[] newVertices = (Vertex<T>[]) new Vertex[newCapacity];
        int[][] newAdjacencyMatrix = new int[newCapacity][newCapacity];

        for (int i = 0; i < vertexCount; i++) {
            newVertices[i] = vertices[i];
            System.arraycopy(adjacencyMatrix[i], 0, newAdjacencyMatrix[i], 0, vertexCount);
        }

        vertices = newVertices;
        adjacencyMatrix = newAdjacencyMatrix;
    }
    public String printVertices() {
        String msg= "";
        for (int i = 0; i < vertexCount; i++) {
            msg+=("Vertex " + (i + 1) + ": " + vertices[i].getValue()+" Conections "+vertices[i].getAdjacent().toString()+"\n");
        }
        return  msg;
    }

    public List<T> dijkstra(T startVertex, T endVertex) {
        int startIndex = getIndex(startVertex);
        int endIndex = getIndex(endVertex);
        System.out.println(startIndex);
        System.out.println(endIndex);
        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("One or both vertices do not exist in the graph.");
        }

        Map<T, Integer> distances = new HashMap<>();
        Map<T, T> previousVertices = new HashMap<>();
        Set<T> visited = new HashSet<>();

        for (int i = 0; i < vertexCount; i++) {
            T vertexValue = vertices[i].getValue();
            distances.put(vertexValue, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        while (!visited.contains(endVertex) && visited.size() < vertexCount) {
            T currentVertex = null;
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < vertexCount; i++) {
                T vertexValue = vertices[i].getValue();
                if (!visited.contains(vertexValue) && distances.get(vertexValue) < minDistance) {
                    currentVertex = vertexValue;
                    minDistance = distances.get(vertexValue);
                }
            }
            visited.add(currentVertex);
            List<Edge<T>> adjacencyList = getAdjacencyListEdges(vertices[getIndex(currentVertex)]);
            for (Edge<T> edge : adjacencyList) {
                T destinationValue = edge.getDestination().getValue();
                int weight = edge.getWeight();
                int newDistance = distances.get(currentVertex) + weight;
                if (newDistance < distances.get(destinationValue)) {
                    distances.put(destinationValue, newDistance);
                    previousVertices.put(destinationValue, currentVertex);
                }
            }
        }

        List<T> shortestPath = new ArrayList<>();
        T currentVertex = endVertex;
        while (currentVertex != null) {
            shortestPath.add(0, currentVertex);
            currentVertex = previousVertices.get(currentVertex);
        }

        return shortestPath;
    }

    public String getAdjacencyMatrixAsString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                int weight = adjacencyMatrix[i][j];
                if (weight != DEFAULT_WEIGHT) {
                    sb.append(String.format("%4d", weight));
                } else {
                    sb.append("  - ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public int[][] floydWarshall() {
        int[][] dist = new int[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (adjacencyMatrix[i][j] != DEFAULT_WEIGHT) {
                    dist[i][j] = adjacencyMatrix[i][j];
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < vertexCount; k++) {
            for (int i = 0; i < vertexCount; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < vertexCount; i++) {
            dist[i][i] = 0;
        }

        return dist;
    }

    public String printFloydWarshall(int[][] dist) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    sb.append("  - ");
                } else {
                    sb.append(String.format("%4d", dist[i][j]));
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }



}
