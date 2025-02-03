package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.NullCellException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    // Test to check if a cell is alive
    @Test
    public void testIsAlive_WhenCellIsAlive_ThenTrue() {
        Cell cell = new Cell(true);
        assertTrue(cell.isAlive());
    }

    // Test to check if a cell is dead
    @Test
    public void testIsAlive_WhenCellIsDead_ThenFalse() {
        Cell cell = new Cell(false);
        assertFalse(cell.isAlive());
    }

    // Test to check if a cell is killed
    @Test
    public void testKill_WhenCellIsAlive_ThenCellIsDead() {
        Cell cell = new Cell(true);
        cell.kill();
        assertFalse(cell.isAlive());
    }

    // Test to check if a cell is revived
    @Test
    public void testRevive_WhenCellIsDead_ThenCellIsAlive() {
        Cell cell = new Cell(false);
        cell.revive();
        assertTrue(cell.isAlive());
    }

}
