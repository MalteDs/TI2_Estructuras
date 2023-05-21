package test;
import model.*;

import junit.framework.TestCase;

import java.util.*;

public class WeightedDirectedGraphTest extends TestCase {
    private WeightedDirectedGraph<String> grafo;

    public void Scenary1() {
        grafo = new WeightedDirectedGraph<>();
    }

    public void Scenary2(){
        Scenary1();
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 1);
    }

    public void Scenary3(){
        Scenary2();
        grafo.addVertex("C");
        grafo.addVertex("D");
        grafo.addEdge("A", "C", 2);
        grafo.addEdge("B", "D", 3);
        grafo.addEdge("C", "D", 2);

    }

    public void Scenary4() {
        Scenary3();
        grafo.addVertex("E");
        grafo.addVertex("F");
        grafo.addVertex("G");
        grafo.addVertex("H");
        grafo.addVertex("I");
        grafo.addEdge("A", "E", 2);
        grafo.addEdge("A", "F", 3);
        grafo.addEdge("B", "G", 2);
        grafo.addEdge("F", "H", 3);
        grafo.addEdge("H", "G", 2);
        grafo.addEdge("G", "I", 3);
        grafo.addEdge("I", "D", 2);
    }

    public void testGetAdjacencyList() {
        Scenary1();
        assertNotNull(grafo.getAdjacencyList());
    }
    public void testGetAdjacencyList2() {
        Scenary3();
        List<Edge<String>> adjacencyList = grafo.getAdjacencyListVertex(grafo.getVertex("A"));
        boolean hasB = false;
        boolean hasC = false;
        boolean hasOtherConnections = false;

        for (Edge<String> edge : adjacencyList) {
            String destinationValue = edge.getDestination().getValue();

            if (destinationValue.equals("B")) {
                hasB = true;
            } else if (destinationValue.equals("C")) {
                hasC = true;
            } else {
                hasOtherConnections = true;
                break;
            }
        }

        assertTrue(hasB);
        assertTrue(hasC);
        assertFalse(hasOtherConnections);
    }

    public void testGetAdjacencyList3() {
        Scenary4();
        List<Edge<String>> adjacencyList = grafo.getAdjacencyListVertex(grafo.getVertex("B"));
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
        grafo.addVertex("A");
        assertTrue(grafo.getAdjacencyList().containsKey(grafo.getVertex("A")));
    }

    public void testAddVertex2() {
        Scenary2();
        grafo.addVertex("C");
        grafo.addEdge("A", "C", 2);
        assertTrue(grafo.getAdjacencyListVertex(grafo.getVertex("A")).contains(new Edge<>(grafo.getVertex("C"), 2)));
    }


    public void testAddVertex3() {
        Scenary2();
        grafo.addVertex("C");
        assertFalse(grafo.getAdjacencyListVertex(grafo.getVertex("C")).contains(new Edge<>(grafo.getVertex("A"), 2)));
    }

    public void testAddEdge() {
        Scenary2();
        assertEquals(2, grafo.getAdjacencyList().size());
    }

    public void testAddEdge2() {
        Scenary2();
        Vertex<String> vertexA = grafo.getVertex("A");
        List<Edge<String>> edgesA = vertexA.getAdjacent();
        for (Edge<String> edge : edgesA) {
            Vertex destination = edge.getDestination();
            int weight = edge.getWeight();
            System.out.println("A -> " + destination.getValue() + " (weight: " + weight + ")");
        }
    }

    public void testAddEdge3() {
        Scenary2();
        grafo.addVertex("C");
        grafo.addEdge("A", "C", 1);
        List<Edge<String>> adjacencyList = grafo.getAdjacencyListEdges(grafo.getVertex("A"));
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
        grafo.removeVertex("A");
        assertEquals(1, grafo.getAdjacencyList().size());
    }

    public void testRemoveVertex2() {
        Scenary3();
        grafo.removeVertex("A");
        assertFalse(grafo.getAdjacencyList().containsKey(new Vertex<>("A")));
    }

    public void testRemoveVertex3() {
        Scenary3();
        grafo.removeVertex("A");
        assertFalse(grafo.getAdjacencyListVertex(grafo.getVertex("B")).contains(grafo.getVertex("A")));
    }

    public void testRemoveEdge() {
        Scenary3();
        grafo.removeEdge("A", "C");
        assertFalse(grafo.getAdjacencyListVertex(grafo.getVertex("A")).contains(grafo.getVertex("C")));
    }

    public void testRemoveEdge2() {
        Scenary3();
        grafo.removeEdge("A", "D");
        assertFalse(grafo.getAdjacencyListVertex(grafo.getVertex("D")).contains(grafo.getVertex("A")));
    }

    public void testRemoveEdge3() {
        Scenary3();
        grafo.removeEdge("A", "D");
        assertFalse(grafo.getAdjacencyListVertex(grafo.getVertex("A")).contains(grafo.getVertex("D")));
    }

    public void testGetVertex() {
        Scenary3();
        assertEquals(new Vertex<>("A"), grafo.getVertex("A"));
    }

    public void testGetVertex2() {
        Scenary3();
        assertEquals(new Vertex<>("B"), grafo.getVertex("B"));
    }

    public void testGetVertex3() {
        Scenary3();
        assertEquals(new Vertex<>("C"), grafo.getVertex("C"));
    }

}
