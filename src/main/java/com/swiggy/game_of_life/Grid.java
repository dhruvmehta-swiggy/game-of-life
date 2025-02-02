package com.swiggy.game_of_life;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.swiggy.game_of_life.Util.createEmptyGrid;

public class Grid {

    // 2D array to store the grid
    private List<List<Cell>> grid;
    private int rows;
    private int columns;

    public Grid(List<List<Cell>> grid) {
        Util.validateGrid(grid);

        this.grid = grid;
        this.rows = grid.size();
        this.columns = grid.get(0).size();
    }

    // Method to count the number of live neighbours of a cell
    public int countLiveNeighbours(int row, int column) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (isWithinBounds(i, j) && !(i == row && j == column)) {
                    if (Cell.isAlive(grid.get(i).get(j))) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // Helper method to calculate bounds of the grid
    private boolean isWithinBounds(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < columns;
    }

    // Method to update the grid for the next generation
    public void nextGenerationUpdate() {
        List<List<Cell>> newGrid = createEmptyGrid(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int liveNeighbours = countLiveNeighbours(i, j);
                boolean isAlive = Cell.isAlive(grid.get(i).get(j));
                if (Util.shouldCellLive(isAlive, liveNeighbours)) {
                    Cell.revive(newGrid.get(i).get(j));
                } else {
                    Cell.kill(newGrid.get(i).get(j));
                }
            }
        }

        grid = newGrid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Grid grid1 = (Grid) obj;

        if (rows != grid1.rows || columns != grid1.columns) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean thisCellState = Cell.isAlive(grid.get(i).get(j));
                boolean grid1CellState = Cell.isAlive(grid1.grid.get(i).get(j));
                if (grid1CellState != thisCellState) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid, rows, columns);
    }
}
