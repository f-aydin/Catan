package com.fatih.catan.domain;

public class Board {
    private final Tile[] tiles;

    public Board() {
        this.tiles =  new Tile[] {
                new Tile(1, 10, Resource.ORE),
                new Tile(2, 2, Resource.WOOL),
                new Tile(3, 9, Resource.LUMBER),
                new Tile(4, 12, Resource.GRAIN),
                new Tile(5, 6, Resource.BRICK),
                new Tile(6, 4, Resource.WOOL),
                new Tile(7, 10, Resource.BRICK),
                new Tile(8, 9, Resource.GRAIN),
                new Tile(9, 11, Resource.LUMBER),
                new Tile(11, 3, Resource.LUMBER),
                new Tile(12, 8, Resource.ORE),
                new Tile(13, 8, Resource.LUMBER),
                new Tile(14, 3, Resource.ORE),
                new Tile(15, 4, Resource.GRAIN),
                new Tile(16, 5, Resource.WOOL),
                new Tile(17, 5, Resource.BRICK),
                new Tile(18, 6, Resource.GRAIN),
                new Tile(19, 11, Resource.WOOL)
        };
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
