package com.fatih.catan.domain;

public class Vertex {
    private Player owner;
    private Building building;
    private Resource resource1;
    private Resource resource2;
    private Resource resource3;
    private Edge edge1;
    private Edge edge2;
    private Edge edge3;

    public Vertex(Resource resource1, Edge edge1, Edge edge2) {
        this.resource1 = resource1;
        this.edge1 = edge1;
        this.edge2 = edge2;
    }

    public Vertex(Resource resource1, Resource resource2, Edge edge1, Edge edge2, Edge edge3) {
        this.resource1 = resource1;
        this.resource2 = resource2;
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
    }

    public Vertex(Resource resource1, Resource resource2, Resource resource3, Edge edge1, Edge edge2, Edge edge3) {
        this.resource1 = resource1;
        this.resource2 = resource2;
        this.resource3 = resource3;
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
    }




}
