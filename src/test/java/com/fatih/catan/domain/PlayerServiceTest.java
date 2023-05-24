package com.fatih.catan.domain;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerServiceTest {

    @Test
    public void filterTilesBasedOnNumber(){
        int dice = 6;
        Board board = new Board();
        List<Tile> filteredTiles = Arrays.stream(board.getTiles()).filter(tile -> tile.getToken() == dice).toList();
        assertEquals(Arrays.asList(board.getTile(5), board.getTile(18)), filteredTiles);
    }

    @Test
    void addingResources(){
        Board board = new Board();

        List<Tile> tilesOnBuilding1 = List.of(new Tile(1, 10, Resource.ORE), new Tile(2, 2, Resource.WOOL), new Tile(5, 6, Resource.BRICK));
        List<Tile> tilesOnBuilding2 = List.of(new Tile(3, 9, Resource.LUMBER), new Tile(6, 4, Resource.WOOL), new Tile(7, 10, Resource.BRICK));

        Building building1 = new Building(tilesOnBuilding1);
        Building building2 = new Building(tilesOnBuilding2);

        Player player1 = new Player(1);
        Player player2 = new Player(2);

        player1.addBuilding(building1);
        player2.addBuilding(building2);

        int dice = 10;

        List<Tile> tiles = Arrays.stream(board.getTiles()).filter(tile -> tile.getToken() == dice).toList();
        List<Player> playerThatReceivesResources;


        for(Tile tile: tiles) {
            playerThatReceivesResources =  Stream.of(player1, player2).filter(player -> player.hasBuilding(tile)).toList();
            for(Player player: playerThatReceivesResources) {
                player.addResource(tile.getResourceType());
            }
        }

        assertAll(
                () -> assertEquals(1, player1.getOre()),
                () -> assertEquals(1, player2.getBrick()),
                () -> assertEquals(0, player1.getGrain()),
                () -> assertEquals(0, player2.getLumber())
        );
    }
}