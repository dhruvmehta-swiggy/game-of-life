package com.swiggy.game_of_life;

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

}
