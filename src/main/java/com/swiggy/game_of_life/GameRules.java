package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;

import java.util.List;

public class GameRules {

    private GameRules() {
        // Private constructor to prevent instantiation
    }

    // Method to check if a cell is alive in the next generation
    public static boolean shouldCellLive(boolean isCurrentlyAlive, int liveNeighbours) {
        if (isCurrentlyAlive) {
            // Cell will survive if it has 2 or 3 live neighbours
            return liveNeighbours == 2 || liveNeighbours == 3;
        } else {
            // A dead cell becomes alive if it has exactly 3 live neighbors
            return liveNeighbours == 3;
        }
    }

    // Method to validate the grid
    public static void validateGrid(List<List<Cell>> grid) {
        if (grid == null) {
            throw new InvalidGridException("Grid cannot be null");
        }

        if (grid.isEmpty()) {
            throw new InvalidGridException("Grid cannot be empty");
        }

        int expectedColumns = grid.get(0).size();
        for (List<Cell> row : grid) {
            if (row.size() != expectedColumns) {
                throw new InvalidGridException("All rows in the grid must have the same number of columns");
            }
        }
    }

}
