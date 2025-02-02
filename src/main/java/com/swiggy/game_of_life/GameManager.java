package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;

public class GameManager {

    private Grid grid;

    public GameManager(Grid grid) {
        if (grid == null) {
            throw new InvalidGridException("Grid cannot be null");
        }

        this.grid = grid;
    }

}
