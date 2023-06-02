package com.fatih.catan.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void doesGetSpecificTileWork() {
        Board board = new Board();
        Tile tileExpected = board.getTiles()[15];
        Tile tileActual = board.getTile(16);

        assertAll(
                () -> assertEquals(tileExpected, tileActual)
        );
    }
}