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
        assertThrows(InvalidGridException.class, () -> {
            new Grid(null);
        });
    }

    // Test to check constructor when grid is empty
    @Test
    public void testConstructor_EmptyGrid_ThrowsInvalidGridException() {
        assertThrows(InvalidGridException.class, () -> {
            new Grid(new ArrayList<>());
        });
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
}
