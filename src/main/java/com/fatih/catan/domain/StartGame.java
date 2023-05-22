package com.fatih.catan.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartGame {

    private Board board;
    private List<Player> players;
    private Player currentPlayer;
    private List<Building> allBuildings;

    public StartGame() {
        this.board = new Board();
        this.players = Arrays.asList(new Player(1), new Player(2), new Player(3), new Player(4));
        this.currentPlayer = players.get(0);
    }

    public void initializeGame(){
        StartGame start = new StartGame();
    }



}
