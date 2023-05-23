package com.fatih.catan.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void doesGetSpecificTileWork() {
        Board board = new Board();
        Tile tileExpected = new Tile(1, 10, Resource.ORE);
        Tile tileActual = board.getSpecificTile(1);
        assertEquals(tileExpected, tileActual);
    }
}