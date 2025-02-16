package com.swiggy.game_of_life;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    // Test to check if a cell is revived
    @Test
    public void testRevive_WhenCellIsDead_ThenCellIsAlive() {
        Cell cell = new Cell(false);
        cell.revive();
        assertTrue(cell.isAlive());
    }

    // Test setNextState for live cell with fewer than 2 live neighbours (Underpopulation)
    @Test
    public void testSetNextState_LiveCellWithFewerThanTwoLiveNeighbours_ThenCellDies() {
        Cell cell = new Cell(true);
        List<Cell> neighbours = Arrays.asList(new Cell(false), new Cell(true), new Cell(false));
        cell.setNextState(neighbours);
        cell.transitionToNextState();
        assertFalse(cell.isAlive());
    }

    // Test setNextState for live cell with 2 or 3 live neighbours (Survival)
    @Test
    public void testSetNextState_LiveCellWithTwoOrThreeLiveNeighbours_ThenCellLives() {
        Cell cell = new Cell(true);
        List<Cell> neighbours = Arrays.asList(new Cell(true), new Cell(true), new Cell(false));
        cell.setNextState(neighbours);
        cell.transitionToNextState();
        assertTrue(cell.isAlive());
    }

    // Test setNextState for live cell with more than 3 live neighbours (Overpopulation)
    @Test
    public void testSetNextState_LiveCellWithMoreThanThreeLiveNeighbours_ThenCellDies() {
        Cell cell = new Cell(true);
        List<Cell> neighbours = Arrays.asList(new Cell(true), new Cell(true), new Cell(true), new Cell(true));
        cell.setNextState(neighbours);
        cell.transitionToNextState();
        assertFalse(cell.isAlive());
    }

    // Test setNextState for dead cell with exactly 3 live neighbours (Reproduction)
    @Test
    public void testSetNextState_DeadCellWithExactlyThreeLiveNeighbours_ThenCellRevives() {
        Cell cell = new Cell(false);
        List<Cell> neighbours = Arrays.asList(new Cell(true), new Cell(true), new Cell(true));
        cell.setNextState(neighbours);
        cell.transitionToNextState();
        assertTrue(cell.isAlive());
    }

    // Test transitionToNextState to ensure state change happens
    @Test
    public void testTransitionToNextState_ThenStateChanges() {
        Cell cell = new Cell(false);
        List<Cell> neighbours = Arrays.asList(new Cell(true), new Cell(true), new Cell(true));
        cell.setNextState(neighbours);
        assertFalse(cell.isAlive());
        cell.transitionToNextState();
        assertTrue(cell.isAlive());
    }
}
