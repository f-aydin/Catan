package com.fatih.catan.domain;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "player_buildings")
public class Building {
    @Id
    @Column(name = "playerID")
    private int playerOwnerID;

    @Column(name = "buildingType")
    private BuildingType type;

    @ManyToMany
    @JoinColumn(name = "tileID")
    private List<Tile> tiles = Arrays.asList(new Tile(1, 10, Resource.ORE), new Tile(2, 2, Resource.WOOL));

    public Building(int playerOwnerID, BuildingType type) {
        this.playerOwnerID = playerOwnerID;
        this.type = type;
    }

    public Building(int playerOwnerID, List<Tile> tiles) {
        this.playerOwnerID = playerOwnerID;
        this.tiles = tiles;
    }

    public Building(int playerOwnerID, BuildingType type, List<Tile> tiles) {
        this.playerOwnerID = playerOwnerID;
        this.type = type;
        this.tiles = tiles;
    }

    public int getPlayerOwnerID() {
        return playerOwnerID;
    }

    public boolean isOnTile(Tile tile) {
        return tiles.contains(tile);
    }
}
