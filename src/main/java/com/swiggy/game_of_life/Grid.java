package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;

import java.util.List;

public class Grid {

    // 2D array to store the grid
    private List<List<Cell>> grid;
    private int rows;
    private int columns;

    public Grid(List<List<Cell>> grid) {
        if (grid == null || grid.isEmpty()) {
            throw new InvalidGridException("Grid cannot be null or empty");
        }

        this.grid = grid;
        this.rows = grid.size();
        this.columns = grid.get(0).size();
    }

}
