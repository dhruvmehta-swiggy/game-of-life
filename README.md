# Game of Life

This project is an implementation of Conway's Game of Life, a cellular automaton devised by mathematician John Conway. The game is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input.

## Rules
- Underpopulation: A live cell with fewer than two live neighbors dies.
- Survival: A live cell with two or three live neighbors lives on to the next generation.
- Overpopulation: A live cell with more than three live neighbors dies.
- Reproduction: A dead cell with exactly three live neighbors becomes a live cell.

## Features

- Initialize a grid with a specified number of rows and columns.
- Seed the grid with a percentage of live cells.
- Simulate the evolution of the grid over generations.
- Stop the simulation when all cells are dead or when the user decides to stop.

### Running the Game

1. Run the `GameManager` class:
2. Follow the prompts to enter the grid dimensions and seed percentage.
3. The game will display the grid and continue to the next generation until all cells are dead or you choose to stop the game.

## Usage

- **Grid Dimensions**: Specify the number of rows and columns for the grid.
- **Seed Percentage**: Enter a percentage (0-100) to determine the initial number of live cells.
- **Stopping the Game**: Enter `-1` when prompted to stop the game manually.
