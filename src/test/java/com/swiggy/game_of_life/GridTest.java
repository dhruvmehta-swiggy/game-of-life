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

    // Helper method to create a grid from a 2D boolean array
    private List<List<Cell>> createGridFromBooleanArray(List<List<Boolean>> array) {
        List<List<Cell>> grid = new ArrayList<>();
        for (List<Boolean> row : array) {
            List<Cell> gridRow = new ArrayList<>();
            for (boolean cellState : row) {
                gridRow.add(new Cell(cellState));
            }
            grid.add(gridRow);
        }
        return grid;
    }

    // Test for a simple still life pattern (Block)
    @Test
    public void testNextGenerationGrid_BlockPattern_ThenUpdateRemainsSame() {
        /*
         * Initial state:
         * [0, 0, 0, 0]
         * [0, 1, 1, 0]
         * [0, 1, 1, 0]
         * [0, 0, 0, 0]
         * */
        List<List<Boolean>> initialState = Arrays.asList(
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, true, true, false),
                Arrays.asList(false, true, true, false),
                Arrays.asList(false, false, false, false)
        );
        List<List<Cell>> actualCells = createGridFromBooleanArray(initialState);
        Grid actualGrid = new Grid(actualCells);

        // Update the grid
        actualGrid.nextGenerationUpdate();

        // Check if the grid remains the same
        assertEquals(new Grid(actualCells), actualGrid);
    }

    // Test for a simple oscillator pattern (Blinker)
    @Test
    public void testNextGenerationGrid_BlinkerPattern_ThenUpdateChanges() {
        /*
         * Initial state:
         * [0, 0, 0, 0, 0]
         * [0, 0, 1, 0, 0]
         * [0, 0, 1, 0, 0]
         * [0, 0, 1, 0, 0]
         * [0, 0, 0, 0, 0]
         * */
        List<List<Boolean>> initialState = Arrays.asList(
                Arrays.asList(false, false, false, false, false),
                Arrays.asList(false, false, true, false, false),
                Arrays.asList(false, false, true, false, false),
                Arrays.asList(false, false, true, false, false),
                Arrays.asList(false, false, false, false, false)
        );
        List<List<Cell>> actualCells = createGridFromBooleanArray(initialState);
        Grid actualGrid = new Grid(actualCells);

        /*
         * Expected state after one update:
         * [0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         * [0, 1, 1, 1, 0]
         * [0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         * */
        List<List<Boolean>> expectedState = Arrays.asList(
                Arrays.asList(false, false, false, false, false),
                Arrays.asList(false, false, false, false, false),
                Arrays.asList(false, true, true, true, false),
                Arrays.asList(false, false, false, false, false),
                Arrays.asList(false, false, false, false, false)
        );
        List<List<Cell>> expectedCells = createGridFromBooleanArray(expectedState);
        Grid expectedGrid = new Grid(expectedCells);

        // Update the grid
        actualGrid.nextGenerationUpdate();

        // Check if the grid matches the expected state
        assertEquals(expectedGrid, actualGrid);
    }

    // Test for a simple spaceship pattern (Glider)
    @Test
    public void testNextGenerationGrid_GliderPattern_ThenUpdateChanges() {
        /*
         * Initial state:
         * [0, 1, 0, 0, 0]
         * [0, 0, 1, 0, 0]
         * [1, 1, 1, 0, 0]
         * [0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         * */
        List<List<Boolean>> initialState = Arrays.asList(
                Arrays.asList(false, true, false, false, false),
                Arrays.asList(false, false, true, false, false),
                Arrays.asList(true, true, true, false, false),
                Arrays.asList(false, false, false, false, false),
                Arrays.asList(false, false, false, false, false)
        );
        List<List<Cell>> actualCells = createGridFromBooleanArray(initialState);
        Grid actualGrid = new Grid(actualCells);

        /*
         * Expected state after one update:
         * [0, 0, 0, 0, 0]
         * [1, 0, 1, 0, 0]
         * [0, 1, 1, 1, 0]
         * [0, 1, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         * */
        List<List<Boolean>> expectedState = Arrays.asList(
                Arrays.asList(false, false, false, false, false),
                Arrays.asList(true, false, true, false, false),
                Arrays.asList(false, true, true, false, false),
                Arrays.asList(false, true, false, false, false),
                Arrays.asList(false, false, false, false, false)
        );
        List<List<Cell>> expectedCells = createGridFromBooleanArray(expectedState);
        Grid expectedGrid = new Grid(expectedCells);

        // Update the grid
        actualGrid.nextGenerationUpdate();

        // Check if the grid matches the expected state
        assertEquals(expectedGrid, actualGrid);
    }

    // Test to check toString method for a grid with 3x3 cells
    @Test
    public void testToString_3x3Grid_ThenReturnStringRepresentation() {
        String actualString = grid.toString();
        String expectedString = " 0  1  0 \n 1  1  0 \n 0  0  1 \n";
        assertEquals(expectedString, actualString);
    }

    // Test to check toString method for a grid with 4x4 cells
    @Test
    public void testToString_4x4Grid_ThenReturnStringRepresentation() {
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false), new Cell(true)),
                Arrays.asList(new Cell(true), new Cell(true), new Cell(false), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true), new Cell(true)),
                Arrays.asList(new Cell(true), new Cell(false), new Cell(true), new Cell(false))
        );
        Grid grid = new Grid(cells);
        String actualString = grid.toString();
        String expectedString = " 0  1  0  1 \n 1  1  0  0 \n 0  0  1  1 \n 1  0  1  0 \n";
        assertEquals(expectedString, actualString);
    }
}
