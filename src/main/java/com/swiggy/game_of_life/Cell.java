package com.swiggy.game_of_life;

import java.util.List;

public class Cell {

    private boolean isAlive;
    private boolean nextState;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
        this.nextState = isAlive;
    }

    // Method to check if a cell is alive
    public boolean isAlive() {
        return this.isAlive;
    }

    // Used for seeding the grid with live cells
    public void revive() {
        this.isAlive = true;
    }

    // Method to transition to the next state
    public void transitionToNextState() {
        this.isAlive = this.nextState;
    }

    // Method to calculate the next state of a cell
    public void setNextState(List<Cell> neighbours) {
        int liveNeighbours = 0;
        for (Cell neighbour : neighbours) {
            if (neighbour.isAlive()) {
                liveNeighbours++;
            }
        }

        if (this.isAlive && (liveNeighbours < 2 || liveNeighbours > 3)) {
            this.nextState = false;
        } else if (!this.isAlive && liveNeighbours == 3) {
            this.nextState = true;
        }
    }
}
