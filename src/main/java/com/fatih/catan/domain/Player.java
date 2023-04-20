package com.fatih.catan.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Entity
@Table(name = "player_resources")
public class Player {

    enum Color {RED, BLUE, GREEN, YELLOW}
    enum Resource {WOOD, ORE, GRAIN, BRICK, WOOL}
    @Id
    @Column(name = "playerID")
    private int playerID;
//    @Column(name = "color")
//    private Color color;
//
//    @Column(name = "hasTurn")
//    private boolean hasTurn;
//
//    @Column(name = "vp")
//    private int victoryPoints;
//
//    @Column(name = "devcards")
//    private ArrayList<String> developmentCards = null;

    @Column(name = "wood")
    private int wood;
    @Column(name = "wool")
    private int wool;
    @Column(name = "brick")
    private int brick;
    @Column(name = "ore")
    private int ore;
    @Column(name = "grain")
    private int grain;

//    public Player(Color color, boolean hasTurn) {
//        this.color = color;
//        this.hasTurn = hasTurn;
//    }

    public int getPlayerID() {
        return playerID;
    }
//
//    public Color getColor() {
//        return color;
//    }
//
//    public boolean getHasTurn() {
//        return hasTurn;
//    }
//
//    public int getVictoryPoints() {
//        return victoryPoints;
//    }
//
//    public ArrayList<String> getDevelopmentCards() {
//        return developmentCards;
//    }

    public int getWood() {
        return wood;
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

    public int[] rollDice() {
        Random rand = new Random();
        return new int[]{rand.nextInt(1, 7), rand.nextInt(1, 7)};
    }
}

