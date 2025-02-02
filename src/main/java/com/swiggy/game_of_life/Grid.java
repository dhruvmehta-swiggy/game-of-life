package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    // 2D array to store the grid
    private List<List<Cell>> grid;
    private int rows;
    private int columns;

    public Grid(List<List<Cell>> grid) {
        GameRules.validateGrid(grid);

        this.grid = grid;
        this.rows = grid.size();
        this.columns = grid.get(0).size();
    }

    // Method to create an empty grid
    public static List<List<Cell>> createEmptyGrid(int rows, int cols) {
        List<List<Cell>> grid = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(new Cell(false)); // Initialize all cells as dead
            }
            grid.add(row);
        }
        return grid;
    }

    // Method to count the number of live neighbours of a cell
    public int countLiveNeighbours(int row, int column) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns && !(i == row && j == column)) {
                    if (Cell.isAlive(grid.get(i).get(j))) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
