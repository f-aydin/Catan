package com.fatih.catan.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "player_resources")
public class Player {

    enum Color {RED, BLUE, GREEN, YELLOW}
    enum Resource {WOOD, ORE, GRAIN, BRICK, WOOL}
    @Id
    @Column(name = "playerID")
    private int playerID;

    @Column(name = "lumber")
    private int lumber;
    @Column(name = "wool")
    private int wool;
    @Column(name = "brick")
    private int brick;
    @Column(name = "ore")
    private int ore;
    @Column(name = "grain")
    private int grain;

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
}

