package com.fatih.catan.domain;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final int number;
    private final Resource resource;
    private final List<Edge> edges;

    public Tile(int number, Resource resource) {
        this.number = number;
        this.resource = resource;
        edges = new ArrayList<>();
    }


    public int getNumber() {
        return number;
    }

    public Resource getResource() {
        return resource;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }
}
