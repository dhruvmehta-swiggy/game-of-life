package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.NullCellException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    // Test to check isAlive method when cell is null
    @Test
    public void testIsAlive_NullCell_ThrowsNullCellException() {
        assertThrows(NullCellException.class, () -> {
            Cell.isAlive(null);
        });
    }

    // Test to check isAlive method when cell is alive
    @Test
    public void testIsAlive_CellIsAlive() {
        Cell cell = new Cell(true);
        assertTrue(Cell.isAlive(cell));
    }

}
