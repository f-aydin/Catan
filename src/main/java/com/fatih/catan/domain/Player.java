package com.fatih.catan.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Entity
@Table(name = "test_table")
public class Player {

    enum Color {RED, BLUE, GREEN, YELLOW}
    enum Resource {WOOD, ORE, GRAIN, BRICK, WOOL}
    @Id
    private int id;
    @Column(name = "color")
    private Color color;

    @Column(name = "hasTurn")
    private boolean hasTurn;

    @Column(name = "vp")
    private int victoryPoints;

//    @Column(name = "resources")
//    private HashMap<Integer, String> resources;

    @Column(name = "devcards")
    private ArrayList<String> developmentCards;

    public Player(Color color, boolean hasTurn, int victoryPoints, HashMap<Integer, String> resources, ArrayList<String> developmentCards) {
        this.color = color;
        this.hasTurn = hasTurn;
        this.victoryPoints = victoryPoints;
//        this.resources = resources;
        this.developmentCards = developmentCards;
    }

    public Color getColor() {
        return color;
    }

    public boolean isHasTurn() {
        return hasTurn;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

//    public HashMap<Integer, String> getResources() {
//        return resources;
//    }

    public ArrayList<String> getDevelopmentCards() {
        return developmentCards;
    }

    public int[] rollDice() {
        Random rand = new Random();
        return new int[]{rand.nextInt(1, 7), rand.nextInt(1, 7)};
    }
}

