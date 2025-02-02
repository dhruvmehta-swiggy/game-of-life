package com.swiggy.game_of_life;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRulesTest {

    // Test to check if a cell with 2 live neighbours survives
    @Test
    public void testShouldCellLive_WhenAliveCell2LiveNeighbours_ThenTrue() {
        boolean isCurrentlyAlive = true;
        int liveNeighbours = 2;
        assertTrue(GameRules.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a cell with 3 live neighbours survives
    @Test
    public void testShouldCellLive_WhenAliveCell3LiveNeighbours_ThenTrue() {
        boolean isCurrentlyAlive = true;
        int liveNeighbours = 3;
        assertTrue(GameRules.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a cell with 1 live neighbour dies
    @Test
    public void testShouldCellLive_WhenAliveCell1LiveNeighbour_ThenFalse() {
        boolean isCurrentlyAlive = true;
        int liveNeighbours = 1;
        assertFalse(GameRules.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a cell with 4 live neighbours dies
    @Test
    public void testShouldCellLive_WhenAliveCell4LiveNeighbours_ThenFalse() {
        boolean isCurrentlyAlive = true;
        int liveNeighbours = 4;
        assertFalse(GameRules.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a dead cell with 3 live neighbours becomes alive
    @Test
    public void testShouldCellLive_WhenDeadCell3LiveNeighbours_ThenTrue() {
        boolean isCurrentlyAlive = false;
        int liveNeighbours = 3;
        assertTrue(GameRules.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a dead cell with 2 live neighbours remains dead
    @Test
    public void testShouldCellLive_WhenDeadCell2LiveNeighbours_ThenFalse() {
        boolean isCurrentlyAlive = false;
        int liveNeighbours = 2;
        assertFalse(GameRules.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }
}
