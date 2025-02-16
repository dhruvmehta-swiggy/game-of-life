package com.swiggy.game_of_life;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grid {

    // 2D array to store the grid
    private final List<List<Cell>> grid;
    private final int rows;
    private final int columns;
    private int liveCellCount;

    public Grid(List<List<Cell>> grid) {
        Util.validateGrid(grid);

        this.grid = grid;
        this.rows = grid.size();
        this.columns = grid.get(0).size();

        liveCellCount = 0;
        for (List<Cell> row : grid) {
            for (Cell cell : row) {
                if (cell.isAlive()) {
                    liveCellCount++;
                }
            }
        }
    }

    // Method to check if least one cell is alive
    public boolean isAnyCellAlive() {
        return liveCellCount > 0;
    }

    // Helper method to calculate bounds of the grid
    private boolean isWithinBounds(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < columns;
    }

    // Method to update the grid for the next generation
    public void nextGenerationUpdate() {
        // First pass: calculate next state
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = grid.get(i).get(j);
                List<Cell> neighbours = getNeighbouringCells(i, j);
                cell.setNextState(neighbours);
            }
        }

        // Second pass: apply next state
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = grid.get(i).get(j);
                boolean currentState = cell.isAlive();
                cell.transitionToNextState();
                if (cell.isAlive() != currentState) {
                    liveCellCount += cell.isAlive() ? 1 : -1;
                }
            }
        }
    }

    // Method to get a list of neighboring cells of the given cell
    private List<Cell> getNeighbouringCells(int row, int column) {
        List<Cell> neighbours = new ArrayList<>();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (isWithinBounds(i, j) && !(i == row && j == column)) {
                    neighbours.add(grid.get(i).get(j));
                }
            }
        }

        return neighbours;
    }

    // Method to return the grid as a string
    @Override
    public String toString() {
        StringBuilder gridString = new StringBuilder();
        for (List<Cell> row : grid) {
            for (Cell cell : row) {
                gridString.append(cell.isAlive() ? " * " : " . ");
            }
            gridString.append("\n");
        }
        return gridString.toString();
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
                Cell cell = grid.get(i).get(j);
                boolean thisCellState = cell.isAlive();
                Cell cell1 = grid1.grid.get(i).get(j);
                boolean grid1CellState = cell1.isAlive();
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
