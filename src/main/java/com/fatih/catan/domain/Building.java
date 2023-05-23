package com.fatih.catan.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildingID;
    private BuildingType buildingType;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Tile> tiles;

    public Building() {
    }

    public Building(int buildingID) {
        this.buildingID = buildingID;
    }

    public Building(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public Building(int buildingID, List<Tile> tiles) {
        this.buildingID = buildingID;
        this.tiles = tiles;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public boolean isOnTile(Tile tile) {
        return tiles.contains(tile);
    }

    public void buildOnTile(Tile tile1){
        tiles.add(tile1);
    }

    public void buildOnTile(Tile tile1, Tile tile2){
        tiles.add(tile1);
        tiles.add(tile2);
    }

    public void buildOnTile(Tile tile1, Tile tile2, Tile tile3){
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
    }
}
