package com.fatih.catan.domain;

import java.util.ArrayList;

public enum Building {
    SETTLEMENT (1),
    CITY (2);

    private final ArrayList<Tile> tiles;

    Building(int victoryPoints){
        tiles = new ArrayList<>();
    }

    public boolean isOnTile(Tile tile) {
        return tiles.contains(tile);
    }
}
