package test;

import model.*;
import junit.framework.TestCase;

import java.util.List;

public class WeightedDirectedGraphMatrixTest extends TestCase {
    private WeightedDirectedGraphMatrix<String> graph;

    public void Scenary1() {
        graph = new WeightedDirectedGraphMatrix<>();
    }

    public void Scenary2() {
        Scenary1();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 1);
    }

    public void Scenary3() {
        Scenary2();
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "D", 2);

    }

    public void Scenary4() {
        Scenary3();
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addEdge("A", "E", 2);
        graph.addEdge("A", "F", 3);
        graph.addEdge("B", "G", 2);
        graph.addEdge("F", "H", 3);
        graph.addEdge("H", "G", 2);
        graph.addEdge("G", "I", 3);
        graph.addEdge("I", "D", 2);
    }

    public void testGetAdjacencyList() {
        Scenary1();
        assertNotNull(graph.getAdjacencyList());
        assertEquals(0, graph.getAdjacencyList().size());
    }

    public void testGetAdjacencyList3() {
        Scenary4();
        List<Edge<String>> adjacencyList = graph.getAdjacencyListVertex(graph.getVertex("B"));
        boolean hasD = false;
        boolean hasG = false;
        boolean hasOtherConnections = false;

        for (Edge<String> edge : adjacencyList) {
            String destinationValue = edge.getDestination().getValue();

            if (destinationValue.equals("D")) {
                hasD = true;
            } else if (destinationValue.equals("G")) {
                hasG = true;
            } else {
                hasOtherConnections = true;
                break;
            }
        }

        assertTrue(hasD);
        assertTrue(hasG);
        assertFalse(hasOtherConnections);
    }

    public void testAddVertex() {
        Scenary1();
        graph.addVertex("A");
        assertTrue(graph.getAdjacencyList().contains("A"));
    }

    public void testAddVertex2() {
        Scenary2();
        graph.addVertex("C");
        graph.addEdge("A", "C", 2);
        assertTrue(graph.getAdjacencyListVertex(graph.getVertex("A")).contains(new Edge<>(graph.getVertex("C"), 2)));
    }

    public void testAddVertex3() {
        Scenary2();
        graph.addVertex("C");
        assertFalse(graph.getAdjacencyListVertex(graph.getVertex("C")).contains(new Edge<>(graph.getVertex("A"), 2)));
    }

    public void testAddEdge() {
        Scenary2();
        assertEquals(1, graph.getAdjacencyListVertex(graph.getVertex("A")).size());
    }

    public void testAddEdge2() {
        Scenary2();
        Vertex<String> vertexA = graph.getVertex("A");
        List<Edge<String>> edgesA = graph.getAdjacencyListVertex(vertexA);
        for (Edge<String> edge : edgesA) {
            Vertex<String> destination = edge.getDestination();
            int weight = edge.getWeight();
            System.out.println("A -> " + destination.getValue() + " (weight: " + weight + ")");
        }
    }

    public void testAddEdge3() {
        Scenary2();
        graph.addVertex("C");
        graph.addEdge("A", "C", 1);
        List<Edge<String>> adjacencyList = graph.getAdjacencyListVertex(graph.getVertex("A"));
        boolean edgeFound = false;

        for (Edge<String> edge : adjacencyList) {
            if (edge.getDestination().getValue().equals("C")) {
                edgeFound = true;
                break;
            }
        }

        assertTrue(edgeFound);
    }

    public void testRemoveVertex() {
        Scenary2();
        graph.removeVertex("A");
        assertEquals(1, graph.getAdjacencyList().size());
    }

    public void testRemoveVertex2() {
        Scenary3();
        graph.removeVertex("A");
        assertFalse(graph.getAdjacencyList().contains("A"));
    }

    public void testRemoveVertex3() {
        Scenary4();
        graph.removeVertex("A");
        Vertex<String> vertexB = graph.getVertex("B");
        List<Edge<String>> adjacencyListB = graph.getAdjacencyListVertex(vertexB);

        boolean edgeFound = false;
        for (Edge<String> edge : adjacencyListB) {
            if (edge.getDestination().equals(vertexB) && edge.getWeight() == 2) {
                edgeFound = true;
                break;
            }
        }

        assertFalse(edgeFound);
    }


    public void testRemoveEdge() {
        Scenary3();
        graph.removeEdge("A", "C");
        assertFalse(graph.getAdjacencyListVertex(graph.getVertex("A")).contains(new Edge<>(graph.getVertex("C"), 2)));
    }

    public void testRemoveEdge2() {
        Scenary3();
        graph.removeEdge("A", "D");
        assertFalse(graph.getAdjacencyListVertex(graph.getVertex("D")).contains(new Edge<>(graph.getVertex("A"), 3)));
    }

    public void testRemoveEdge3() {
        Scenary3();
        graph.removeEdge("A", "D");
        assertFalse(graph.getAdjacencyListVertex(graph.getVertex("A")).contains(new Edge<>(graph.getVertex("D"), 3)));
    }

    public void testGetVertex() {
        Scenary3();
        assertEquals(new Vertex<>("A"), graph.getVertex("A"));
    }

    public void testGetVertex2() {
        Scenary3();
        assertEquals(new Vertex<>("B"), graph.getVertex("B"));
    }

    public void testGetVertex3() {
        Scenary3();
        assertEquals(new Vertex<>("C"), graph.getVertex("C"));
    }

    public void testDijkstra() {
        Scenary2();
        String startVertex = "A";
        String endVertex = "B";

        List<String> shortestPath = graph.dijkstra(startVertex, endVertex);

        assertEquals(2, shortestPath.size());
        assertEquals(startVertex, shortestPath.get(0));
        assertEquals(endVertex, shortestPath.get(1));
    }

    public void testDijkstra2() {
        Scenary3();
        String startVertex = "A";
        String endVertex = "D";

        List<String> shortestPath = graph.dijkstra(startVertex, endVertex);

        assertEquals(3, shortestPath.size());
        assertEquals(startVertex, shortestPath.get(0));
        assertEquals("B", shortestPath.get(1));
        assertEquals(endVertex, shortestPath.get(2));
    }

    public void testDijkstra3() {
        Scenary4();
        String startVertex = "A";
        String endVertex = "H";

        List<String> shortestWay = graph.dijkstra(startVertex, endVertex);

        assertEquals(3, shortestWay.size());
        assertEquals(startVertex, shortestWay.get(0));
        assertEquals("F", shortestWay.get(1));
        assertEquals(endVertex, shortestWay.get(2));
    }

    public void testFloydWarshall1() {
        Scenary2();
        int[][] expectedDistances = {
                {0, 1},
                {Integer.MAX_VALUE, 0}
        };

        int[][] distances = graph.floydWarshall();
        for (int i = 0; i < expectedDistances.length; i++) {
            for (int j = 0; j < expectedDistances[0].length; j++) {
                assertEquals(expectedDistances[i][j], distances[i][j]);
            }
        }
    }

    public void testFloydWarshall2() {
        Scenary3();
        int[][] expectedDistances = {
                {0, 1, 2, 4},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 3},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 2},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        int[][] distances = graph.floydWarshall();
        for (int i = 0; i < expectedDistances.length; i++) {
            for (int j = 0; j < expectedDistances[0].length; j++) {
                assertEquals(expectedDistances[i][j], distances[i][j]);
            }
        }
    }

    public void testFloydWarshall3() {
        Scenary4();
        int[][] expectedDistances = {
                {0, 1, 2, 4, 2, 3, 3, 6, 6},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, Integer.MAX_VALUE, 5},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 2, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 10, Integer.MAX_VALUE, 0, 5, 3, 8},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 5, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 3},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 7, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 0, 5},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        int[][] distances = graph.floydWarshall();
        for (int i = 0; i < expectedDistances.length; i++) {
            for (int j = 0; j < expectedDistances[0].length; j++) {
                assertEquals(expectedDistances[i][j], distances[i][j]);
            }
        }
    }
}
