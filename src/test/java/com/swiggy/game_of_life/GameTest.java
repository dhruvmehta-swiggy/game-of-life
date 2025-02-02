package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    // Test to check Game constructor when grid is null
    @Test
    public void testConstructor_NullGrid_ThrowsInvalidGridException() {
        assertThrows(InvalidGridException.class, () -> {
            new Game(null);
        });
    }

    // Test to check Game constructor when grid is valid
    @Test
    public void testConstructor_ValidGrid_DoesNotThrowException() {
        List<List<Cell>> arr = new ArrayList<>();
        List<Cell> row = new ArrayList<>();
        row.add(new Cell(false));
        arr.add(row);
        Grid grid = new Grid(arr);

        assertDoesNotThrow(() -> {
            new Game(grid);
        });
    }
}
