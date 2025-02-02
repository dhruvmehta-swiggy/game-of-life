package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {

    // Test to check if a cell with 2 live neighbours survives
    @Test
    public void testShouldCellLive_WhenAliveCell2LiveNeighbours_ThenTrue() {
        boolean isCurrentlyAlive = true;
        int liveNeighbours = 2;
        assertTrue(Util.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a cell with 3 live neighbours survives
    @Test
    public void testShouldCellLive_WhenAliveCell3LiveNeighbours_ThenTrue() {
        boolean isCurrentlyAlive = true;
        int liveNeighbours = 3;
        assertTrue(Util.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a cell with 1 live neighbour dies
    @Test
    public void testShouldCellLive_WhenAliveCell1LiveNeighbour_ThenFalse() {
        boolean isCurrentlyAlive = true;
        int liveNeighbours = 1;
        assertFalse(Util.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a cell with 4 live neighbours dies
    @Test
    public void testShouldCellLive_WhenAliveCell4LiveNeighbours_ThenFalse() {
        boolean isCurrentlyAlive = true;
        int liveNeighbours = 4;
        assertFalse(Util.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a dead cell with 3 live neighbours becomes alive
    @Test
    public void testShouldCellLive_WhenDeadCell3LiveNeighbours_ThenTrue() {
        boolean isCurrentlyAlive = false;
        int liveNeighbours = 3;
        assertTrue(Util.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check if a dead cell with 2 live neighbours remains dead
    @Test
    public void testShouldCellLive_WhenDeadCell2LiveNeighbours_ThenFalse() {
        boolean isCurrentlyAlive = false;
        int liveNeighbours = 2;
        assertFalse(Util.shouldCellLive(isCurrentlyAlive, liveNeighbours));
    }

    // Test to check validateGrid method with null grid
    @Test
    public void testValidateGrid_WhenGridIsNull_ThrowsInvalidGridException() {
        assertThrows(InvalidGridException.class, () -> Util.validateGrid(null));
    }

    // Test to check validateGrid method with empty grid
    @Test
    public void testValidateGrid_WhenGridIsEmpty_ThrowsInvalidGridException() {
        assertThrows(InvalidGridException.class, () -> Util.validateGrid(new ArrayList<>()));
    }

    // Test to check validateGrid method with grid having different number of columns
    @Test
    public void testValidateGrid_WhenGridHasDifferentColumns_ThrowsInvalidGridException() {
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true))
        );
        assertThrows(InvalidGridException.class, () -> Util.validateGrid(cells));
    }

    // Test to check validateGrid method with valid grid
    @Test
    public void testValidateGrid_WhenGridIsValid() {
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true))
        );
        assertDoesNotThrow(() -> Util.validateGrid(cells));
    }

    // Test to check createEmptyGrid method when rows and columns are 3
    @Test
    public void testCreateEmptyGrid_Then3x3Grid() {
        List<List<Cell>> emptyGrid = Util.createEmptyGrid(3, 3);

        // Check if the grid is 3x3
        assertEquals(3, emptyGrid.size());

        // Check if each row has 3 columns
        for (List<Cell> row : emptyGrid) {
            assertEquals(3, row.size());
        }

        // Check if all cells are dead
        for (List<Cell> row : emptyGrid) {
            for (Cell cell : row) {
                assertFalse(Cell.isAlive(cell));
            }
        }
    }

    // Test to check createEmptyGrid method when rows and columns are 0
    @Test
    public void testCreateEmptyGrid_ZeroRowsAndColumns_ThenEmptyGrid() {
        List<List<Cell>> emptyGrid = Util.createEmptyGrid(0, 0);
        assertTrue(emptyGrid.isEmpty());
    }
}
