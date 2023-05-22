package com.fatih.catan.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerServiceTest {
    @Autowired
    private JpaRepository<Player, Integer> playerRepository;

    @Test
    public void filterTilesBasedOnNumber(){
        int dice = 6;
        Tile[] tiles = new Tile[] {
                new Tile(1, 10, Resource.ORE),
                new Tile(2, 2, Resource.WOOL),
                new Tile(3, 9, Resource.LUMBER),
                new Tile(4, 12, Resource.GRAIN),
                new Tile(5, 6, Resource.BRICK),
                new Tile(6, 4, Resource.WOOL),
                new Tile(7, 10, Resource.BRICK),
                new Tile(8, 9, Resource.GRAIN),
                new Tile(9, 11, Resource.LUMBER),
                new Tile(10, 3, Resource.LUMBER),
                new Tile(11, 8, Resource.ORE),
                new Tile(12, 8, Resource.LUMBER),
                new Tile(13, 3, Resource.ORE),
                new Tile(14, 4, Resource.GRAIN),
                new Tile(15, 5, Resource.WOOL),
                new Tile(16, 5, Resource.BRICK),
                new Tile(17, 6, Resource.GRAIN),
                new Tile(18, 11, Resource.WOOL)
        };
        List<Tile> filteredTiles = Arrays.stream(tiles).filter(tile -> tile.getToken() == dice).toList();
        assertEquals(Arrays.asList(tiles[4], tiles[16]), filteredTiles);
    }

    @Test
    public void test1(){
        List<Player> players = playerRepository.findAll();
        assertEquals(1, players.get(0).getPlayerID());
    }

}