package com.fatih.catan.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerID;
    private int lumber;
    private int wool;
    private int brick;
    private int ore;
    private int grain;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Building> buildings = new ArrayList<>();

    public Player() {
    }

    public Player(int playerID) {
        this.playerID = playerID;
    }

    public Player(int playerID, List<Building> buildings) {
        this.playerID = playerID;
        this.buildings = buildings;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getLumber() {
        return lumber;
    }

    public int getWool() {
        return wool;
    }

    public int getBrick() {
        return brick;
    }

    public int getOre() {
        return ore;
    }

    public int getGrain() {
        return grain;
    }

    public void setLumber(int lumber) {
        this.lumber = lumber;
    }

    public void setWool(int wool) {
        this.wool = wool;
    }

    public void setBrick(int brick) {
        this.brick = brick;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public void setGrain(int grain) {
        this.grain = grain;
    }

    public void addResource(Resource resource) {
        switch(resource) {
            case LUMBER -> {
                lumber++;
            }

            case ORE -> {
                ore++;
            }
            case GRAIN -> {
                grain++;
            }
            case BRICK -> {
                brick++;
            }
            case WOOL -> {
                wool++;
            }
        }
    }

    public boolean hasBuilding(Tile tile) {
        return buildings.stream().anyMatch(building -> building.isOnTile(tile));
    }


    public void addBuilding(Building building){
        buildings.add(building);
    }

}

