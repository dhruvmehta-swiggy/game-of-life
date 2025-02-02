package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    // Test to check validateGrid method with null grid
    @Test
    public void testValidateGrid_WhenGridIsNull_ThrowsInvalidGridException() {
        assertThrows(InvalidGridException.class, () -> GameRules.validateGrid(null));
    }

    // Test to check validateGrid method with empty grid
    @Test
    public void testValidateGrid_WhenGridIsEmpty_ThrowsInvalidGridException() {
        assertThrows(InvalidGridException.class, () -> GameRules.validateGrid(new ArrayList<>()));
    }

    // Test to check validateGrid method with grid having different number of columns
    @Test
    public void testValidateGrid_WhenGridHasDifferentColumns_ThrowsInvalidGridException() {
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true))
        );
        assertThrows(InvalidGridException.class, () -> GameRules.validateGrid(cells));
    }

    // Test to check validateGrid method with valid grid
    @Test
    public void testValidateGrid_WhenGridIsValid() {
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true))
        );
        assertDoesNotThrow(() -> GameRules.validateGrid(cells));
    }
}
