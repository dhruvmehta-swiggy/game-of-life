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

    // Test for a simple still life pattern (Block) over 5 generations
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

        // Expected states for 5 generations
        List<List<List<Boolean>>> generations = Arrays.asList(
                /*
                 * Generation 1:
                 * [0, 0, 0, 0]
                 * [0, 1, 1, 0]
                 * [0, 1, 1, 0]
                 * [0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false),
                        Arrays.asList(false, true, true, false),
                        Arrays.asList(false, true, true, false),
                        Arrays.asList(false, false, false, false)
                ),
                /*
                 * Generation 2:
                 * [0, 0, 0, 0]
                 * [0, 1, 1, 0]
                 * [0, 1, 1, 0]
                 * [0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false),
                        Arrays.asList(false, true, true, false),
                        Arrays.asList(false, true, true, false),
                        Arrays.asList(false, false, false, false)
                ),
                /*
                 * Generation 3:
                 * [0, 0, 0, 0]
                 * [0, 1, 1, 0]
                 * [0, 1, 1, 0]
                 * [0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false),
                        Arrays.asList(false, true, true, false),
                        Arrays.asList(false, true, true, false),
                        Arrays.asList(false, false, false, false)
                ),
                /*
                 * Generation 4:
                 * [0, 0, 0, 0]
                 * [0, 1, 1, 0]
                 * [0, 1, 1, 0]
                 * [0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false),
                        Arrays.asList(false, true, true, false),
                        Arrays.asList(false, true, true, false),
                        Arrays.asList(false, false, false, false)
                )
        );

        // Update grid and check each generation
        for (List<List<Boolean>> expectedState : generations) {
            actualGrid.nextGenerationUpdate();
            List<List<Cell>> expectedCells = createGridFromBooleanArray(expectedState);
            Grid expectedGrid = new Grid(expectedCells);

            assertEquals(expectedGrid, actualGrid);
        }
    }

    // Test for a simple oscillator pattern (Blinker) over 5 generations
    @Test
    public void testNextGenerationGrid_BlinkerPattern_ThenUpdateChanges() {
        /*
         * Initial state (Generation 0):
         * [0, 0, 0, 0, 0]
         * [0, 0, 1, 0, 0]
         * [0, 0, 1, 0, 0]
         * [0, 0, 1, 0, 0]
         * [0, 0, 0, 0, 0]
         */
        List<List<Boolean>> initialState = Arrays.asList(
                Arrays.asList(false, false, false, false, false),
                Arrays.asList(false, false, true, false, false),
                Arrays.asList(false, false, true, false, false),
                Arrays.asList(false, false, true, false, false),
                Arrays.asList(false, false, false, false, false)
        );
        List<List<Cell>> actualCells = createGridFromBooleanArray(initialState);
        Grid actualGrid = new Grid(actualCells);

        // Expected states for 5 generations
        List<List<List<Boolean>>> generations = Arrays.asList(
                /*
                 * Generation 1:
                 * [0, 0, 0, 0, 0]
                 * [0, 0, 0, 0, 0]
                 * [0, 1, 1, 1, 0]
                 * [0, 0, 0, 0, 0]
                 * [0, 0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, true, true, true, false),
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false)
                ),
                /*
                 * Generation 2:
                 * [0, 0, 0, 0, 0]
                 * [0, 0, 1, 0, 0]
                 * [0, 0, 1, 0, 0]
                 * [0, 0, 1, 0, 0]
                 * [0, 0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, true, false, false),
                        Arrays.asList(false, false, true, false, false),
                        Arrays.asList(false, false, true, false, false),
                        Arrays.asList(false, false, false, false, false)
                ),
                /*
                 * Generation 3:
                 * [0, 0, 0, 0, 0]
                 * [0, 0, 0, 0, 0]
                 * [0, 1, 1, 1, 0]
                 * [0, 0, 0, 0, 0]
                 * [0, 0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, true, true, true, false),
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false)
                ),
                /*
                 * Generation 4:
                 * [0, 0, 0, 0, 0]
                 * [0, 0, 1, 0, 0]
                 * [0, 0, 1, 0, 0]
                 * [0, 0, 1, 0, 0]
                 * [0, 0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, true, false, false),
                        Arrays.asList(false, false, true, false, false),
                        Arrays.asList(false, false, true, false, false),
                        Arrays.asList(false, false, false, false, false)
                )
        );

        // Update grid and check each generation
        for (List<List<Boolean>> expectedState : generations) {
            actualGrid.nextGenerationUpdate();
            List<List<Cell>> expectedCells = createGridFromBooleanArray(expectedState);
            Grid expectedGrid = new Grid(expectedCells);

            assertEquals(expectedGrid, actualGrid);
        }
    }

    // Test for a simple spaceship pattern (Glider) over 5 generations
    @Test
    public void testNextGenerationGrid_GliderPattern_FiveGenerations() {
        /*
         * Initial state (Generation 0):
         * [0, 1, 0, 0, 0]
         * [0, 0, 1, 0, 0]
         * [1, 1, 1, 0, 0]
         * [0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         */
        List<List<Boolean>> initialState = Arrays.asList(
                Arrays.asList(false, true, false, false, false),
                Arrays.asList(false, false, true, false, false),
                Arrays.asList(true, true, true, false, false),
                Arrays.asList(false, false, false, false, false),
                Arrays.asList(false, false, false, false, false)
        );
        List<List<Cell>> actualCells = createGridFromBooleanArray(initialState);
        Grid actualGrid = new Grid(actualCells);

        // Expected states for 5 generations
        List<List<List<Boolean>>> generations = Arrays.asList(
                /*
                 * Generation 1:
                 * [0, 0, 0, 0, 0]
                 * [1, 0, 1, 0, 0]
                 * [0, 1, 1, 0, 0]
                 * [0, 1, 0, 0, 0]
                 * [0, 0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(true, false, true, false, false),
                        Arrays.asList(false, true, true, false, false),
                        Arrays.asList(false, true, false, false, false),
                        Arrays.asList(false, false, false, false, false)
                ),
                /*
                 * Generation 2:
                 * [0, 0, 0, 0, 0]
                 * [0, 0, 1, 0, 0]
                 * [1, 0, 1, 0, 0]
                 * [0, 1, 1, 0, 0]
                 * [0, 0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, true, false, false),
                        Arrays.asList(true, false, true, false, false),
                        Arrays.asList(false, true, true, false, false),
                        Arrays.asList(false, false, false, false, false)
                ),
                /*
                 * Generation 3:
                 * [0, 0, 0, 0, 0]
                 * [0, 1, 0, 0, 0]
                 * [0, 0, 1, 1, 0]
                 * [0, 1, 1, 0, 0]
                 * [0, 0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, true, false, false, false),
                        Arrays.asList(false, false, true, true, false),
                        Arrays.asList(false, true, true, false, false),
                        Arrays.asList(false, false, false, false, false)
                ),
                /*
                 * Generation 4:
                 * [0, 0, 0, 0, 0]
                 * [0, 0, 1, 0, 0]
                 * [0, 0, 0, 1, 0]
                 * [0, 1, 1, 1, 0]
                 * [0, 0, 0, 0, 0]
                 */
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, true, false, false),
                        Arrays.asList(false, false, false, true, false),
                        Arrays.asList(false, true, true, true, false),
                        Arrays.asList(false, false, false, false, false)
                )
        );

        // Update grid and check each generation
        for (List<List<Boolean>> expectedState : generations) {
            actualGrid.nextGenerationUpdate();
            List<List<Cell>> expectedCells = createGridFromBooleanArray(expectedState);
            Grid expectedGrid = new Grid(expectedCells);

            assertEquals(expectedGrid, actualGrid);
        }
    }


    // Test to check toString method for a grid with 3x3 cells
    @Test
    public void testToString_3x3Grid_ThenReturnStringRepresentation() {
        String actualString = grid.toString();
        String expectedString = " .  *  . \n *  *  . \n .  .  * \n";
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
        String expectedString = " .  *  .  * \n *  *  .  . \n .  .  *  * \n *  .  *  . \n";
        assertEquals(expectedString, actualString);
    }
}
