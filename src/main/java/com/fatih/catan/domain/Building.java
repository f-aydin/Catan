package com.fatih.catan.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildingID;
    private BuildingType type;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Tile> tiles = new ArrayList<>();

    public Building() {
    }

    public Building(BuildingType type, List<Tile> tiles) {
        this.type = type;
        this.tiles = tiles;
    }

    public Building(List<Tile> tiles) {
        this.type = BuildingType.SETTLEMENT;
        this.tiles = tiles;
    }

    public Building(int buildingID, List<Tile> tiles) {
        this.buildingID = buildingID;
        this.tiles = tiles;
    }
    public int getBuildingID() {
        return buildingID;
    }

    public BuildingType getType() {
        return type;
    }

    public boolean isOnTile(Tile tile) {
        return tiles.contains(tile);
    }
}
