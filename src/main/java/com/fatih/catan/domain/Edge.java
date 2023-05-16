package com.fatih.catan.domain;

public class Edge {
    private Player owner;
    private boolean isRoadBuilt;
    private Vertex vertex1;
    private Vertex vertex2;

    public Edge(Vertex vertex1, Vertex vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
}
