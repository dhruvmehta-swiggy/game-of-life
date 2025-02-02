package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    private Grid grid;

    @BeforeEach
    public void setUp() {
        /*
          Initialize a 3x3 grid:
          [0, 1, 0]
          [1, 1, 0]
          [0, 0, 1]
        */
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true))
        );
        grid = new Grid(cells);
    }

    // Test to check constructor when grid is null
    @Test
    public void testConstructor_NullGrid_ThrowsInvalidGridException() {
        assertThrows(InvalidGridException.class, () -> new Grid(null));
    }

    // Test to check constructor when grid is empty
    @Test
    public void testConstructor_EmptyGrid_ThrowsInvalidGridException() {
        assertThrows(InvalidGridException.class, () -> new Grid(new ArrayList<>()));
    }

    // Test to check constructor when grid is valid
    @Test
    public void testConstructor_ValidGrid() {
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true))
        );
        Grid grid = new Grid(cells);
        assertNotNull(grid);
    }

    // Test to check countLiveNeighbours method when cell is at the center (1, 1)
    @Test
    public void testCountLiveNeighbours_CenterCell_Then3LiveNeighbours() {
        int liveNeighbours = grid.countLiveNeighbours(1, 1);
        assertEquals(3, liveNeighbours);
    }

    // Test to check countLiveNeighbours method when cell is in the top left corner (0, 0)
    @Test
    public void testCountLiveNeighbours_TopLeftCornerCell_Then3LiveNeighbour() {
        int liveNeighbours = grid.countLiveNeighbours(0, 0);
        assertEquals(3, liveNeighbours);
    }

    // Test to check countLiveNeighbours method when cell is in the top right corner (0, 2)
    @Test
    public void testCountLiveNeighbours_TopRightCornerCell_Then2LiveNeighbour() {
        int liveNeighbours = grid.countLiveNeighbours(0, 2);
        assertEquals(2, liveNeighbours);
    }

    // Test to check countLiveNeighbours method when cell is in the bottom left corner (2, 0)
    @Test
    public void testCountLiveNeighbours_BottomLeftCornerCell_Then2LiveNeighbour() {
        int liveNeighbours = grid.countLiveNeighbours(2, 0);
        assertEquals(2, liveNeighbours);
    }

    // Test to check createEmptyGrid method when rows and columns are 3
    @Test
    public void testCreateEmptyGrid_Then3x3Grid() {
        List<List<Cell>> emptyGrid = Grid.createEmptyGrid(3, 3);

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
        List<List<Cell>> emptyGrid = Grid.createEmptyGrid(0, 0);
        assertTrue(emptyGrid.isEmpty());
    }

    // Test to check equals method when two grids are equal
    @Test
    public void testEquals_TwoEqualGrids_ThenTrue() {
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true))
        );
        Grid otherGrid = new Grid(cells);
        assertEquals(grid, otherGrid);
    }

    // Test to check equals method when two grids are not equal
    @Test
    public void testEquals_TwoUnequalGrids_ThenFalse() {
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(false))
        );
        Grid otherGrid = new Grid(cells);
        assertNotEquals(grid, otherGrid);
    }
}
