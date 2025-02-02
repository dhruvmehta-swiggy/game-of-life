package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GridTest {

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
        List<List<Cell>> validGrid = new ArrayList<>();
        List<Cell> row = new ArrayList<>();
        row.add(new Cell(false)); // Assuming Cell has a default constructor
        validGrid.add(row);

        assertDoesNotThrow(() -> {
            new Grid(validGrid);
        });
    }
}
