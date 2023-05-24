package com.fatih.catan.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void hasBuildingReturnsTrueIfBuildingIsOnTile() {
        Tile tile1 = new Tile(1, 10, Resource.ORE);
        Tile tile2 = new Tile(2, 2, Resource.WOOL);
        Tile tile3 = new Tile(5, 6, Resource.BRICK);

        List<Building> buildings = List.of(new Building(1, Arrays.asList(tile1, tile2, tile3)));


        assertTrue(buildings.stream().anyMatch(building -> building.isOnTile(tile2)));
    }

    @Test
    void addResourceAddsOneResourceToResourceType() {
        Player player = new Player();
        player.addResource(Resource.BRICK);
        assertEquals(1, player.getBrick());
    }
}