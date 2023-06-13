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
    @JoinColumn(name = "")
    private List<Building> buildings;
    private boolean hasTurn;
    private int victoryPoints;
    @OneToMany(cascade = {CascadeType.ALL})
    @Enumerated(EnumType.STRING)
    private List<Card> devCards = new ArrayList<>();

    public Player() {
    }

    public Player(int playerID) {
        this.playerID = playerID;
    }

    public Player(int playerID, int lumber, int wool, int brick, int ore, int grain, List<Building> buildings, boolean hasTurn) {
        this.playerID = playerID;
        this.lumber = lumber;
        this.wool = wool;
        this.brick = brick;
        this.ore = ore;
        this.grain = grain;
        this.buildings = buildings;
        this.hasTurn = hasTurn;
    }

    public Player(int playerID, List<Building> buildings) {
        this.playerID = playerID;
        this.buildings = buildings;
    }

    public Player(int playerID, int lumber, int wool, int brick, int ore, int grain, boolean hasTurn) {
        this.playerID = playerID;
        this.lumber = lumber;
        this.wool = wool;
        this.brick = brick;
        this.ore = ore;
        this.grain = grain;
        this.hasTurn = hasTurn;
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

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public List<Card> getDevCards() {
        return devCards;
    }

    public List<Building> getBuildings() {
        return buildings;
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

    public boolean isHasTurn() {
        return hasTurn;
    }

    public void setHasTurn(boolean hasTurn) {
        this.hasTurn = hasTurn;
    }

    public void addResource(Resource resource, int howManyResourceToGet) {
        switch(resource) {
            case LUMBER -> {
                lumber += howManyResourceToGet;
            }
            case ORE -> {
                ore += howManyResourceToGet;
            }
            case GRAIN -> {
                grain += howManyResourceToGet;
            }
            case BRICK -> {
                brick += howManyResourceToGet;
            }
            case WOOL -> {
                wool += howManyResourceToGet;
            }
            case NULL -> {}
        }
    }

    public void setResourceToZero(Resource resource) {
        switch(resource) {
            case LUMBER -> {
                lumber = 0;
            }
            case ORE -> {
                ore = 0;
            }
            case GRAIN -> {
                grain = 0;
            }
            case BRICK -> {
                brick = 0;
            }
            case WOOL -> {
                wool = 0;
            }
        }
    }

    public void addCard(Card card){
        devCards.add(card);
    }

    public void addVictoryPoint(){
        victoryPoints++;
    }

    public boolean hasBuilding(Tile tile) {
        return buildings.stream()
                .anyMatch(building -> building.isOnTile(tile));
    }

    public int howManyBuildingsOnTile(Tile tile){
        return buildings.stream()
                .filter(building -> building.isOnTile(tile))
                .toList().size();
    }

    public void addBuilding(Building building){
        buildings.add(building);
    }

}

