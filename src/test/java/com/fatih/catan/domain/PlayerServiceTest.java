package com.fatih.catan.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceTest {

    @Test
    public void filterTilesBasedOnNumber(){
        int dice = 6;
        Board board = new Board();
        List<Tile> filteredTiles = Arrays.stream(board.getTiles()).filter(tile -> tile.getToken() == dice).toList();
        assertEquals(Arrays.asList(board.getTile(5), board.getTile(18)), filteredTiles);
    }

    @Test
    public void filterPlayersBasedOnBuildings(){
        List<Tile> tilesBuilding1 = List.of(new Tile(1, 10, Resource.ORE), new Tile(2, 2, Resource.WOOL), new Tile(5, 6, Resource.BRICK));
        Building building1 = new Building(tilesBuilding1);
        List<Tile> tilesBuilding2 = List.of(new Tile(2, 2, Resource.WOOL),  new Tile(3, 9, Resource.LUMBER), new Tile(6, 4, Resource.WOOL));
        Building building2 = new Building(tilesBuilding2);

        Player player1 = new Player(1, List.of(building1, building2));
        Player player2 = new Player(1, List.of(building2));

        List<Player> players = List.of(player1, player2);
        Tile tile = new Tile(2, 2, Resource.WOOL);
        assertAll(
                () -> assertEquals(2, player1.howManyBuildingsOnTile(tile)),
                () -> assertEquals(1, player2.howManyBuildingsOnTile(tile))
        );
    }

    @Test
    void addingResources(){
        Board board = new Board();

        List<Tile> tilesBuilding1 = List.of(new Tile(1, 10, Resource.ORE), new Tile(2, 2, Resource.WOOL), new Tile(5, 6, Resource.BRICK));
        Building building1 = new Building(tilesBuilding1);

        List<Tile> tilesBuilding2 = List.of(new Tile(2, 2, Resource.WOOL),  new Tile(3, 9, Resource.LUMBER), new Tile(6, 4, Resource.WOOL));
        Building building2 = new Building(tilesBuilding2);

        Player player1 = new Player(1, List.of(building1, building2));
        Player player2 = new Player(1, List.of(building2));

        int dice = 2;
        List<Tile> tiles = Arrays.stream(board.getTiles()).filter(tile -> tile.getToken() == dice).toList();
        List<Player> playerThatReceivesResources;

        for(Tile tile: tiles) {
            playerThatReceivesResources =  Stream.of(player1, player2).filter(player -> player.hasBuilding(tile)).toList();
            for(Player player: playerThatReceivesResources) {
                player.addResource(tile.getResourceType(), player.howManyBuildingsOnTile(tile));
            }
        }

        assertAll(
                () -> assertEquals(0, player1.getOre()),
                () -> assertEquals(0, player2.getBrick()),
                () -> assertEquals(2, player1.getWool()),
                () -> assertEquals(1, player2.getWool()),
                () -> assertEquals(0, player1.getGrain()),
                () -> assertEquals(0, player2.getLumber())
        );
    }
}