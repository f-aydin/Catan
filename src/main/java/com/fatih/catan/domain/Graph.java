package com.fatih.catan.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    private static Map<Vertex, List<Vertex>> adjVertices;

    public void addVertex(String label){
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public  void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
    }

    public void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    public void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    public static Graph createGraph() {
        Graph graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
        return graph;
    }

    public static List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    public static void main(String[] args) {
        createGraph();
        System.out.println(getAdjVertices("Bob"));
    }

}
