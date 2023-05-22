package com.fatih.catan.domain;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "player_buildings")
public class Building {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinColumn()
    private List<Tile> tiles;

    public Building(int id) {
        this.id = id;
    }

    public Building(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public Building(int id, List<Tile> tiles) {
        this.id = id;
        this.tiles = tiles;
    }

    public int getId() {
        return id;
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
