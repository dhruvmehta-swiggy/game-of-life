package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.NullCellException;

public class Cell {

    private boolean isAlive;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }


    // Method to check if a cell is alive
    public boolean isAlive() {
        return this.isAlive;
    }

    // Method to make cell alive
    public static void kill(Cell cell) {
        if (cell == null) {
            throw new NullCellException("Cell cannot be null");
        }
        cell.isAlive = false;
    }

    // Method to make cell dead
    public static void revive(Cell cell) {
        if (cell == null) {
            throw new NullCellException("Cell cannot be null");
        }
        cell.isAlive = true;
    }
}
