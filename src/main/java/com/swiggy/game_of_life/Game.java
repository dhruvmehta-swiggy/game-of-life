package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;

import java.util.List;
import java.util.Scanner;

public class Game {

    // Constants
    private static final String CURRENT_GRID_MESSAGE = "Current Grid:";
    private static final String STOP_GAME_PROMPT = "Enter -1 to stop the game, or any other number to continue:";
    private static final String ALL_CELLS_DEAD_MESSAGE = "All cells are dead. Stopping the game.";
    private static final String GAME_STOPPED_BY_USER_MESSAGE = "Game stopped by user.";
    private static final String INVALID_GRID_EXCEPTION_MESSAGE = "Invalid grid dimensions or seed percentage";
    private static final String INVALID_SCANNER_EXCEPTION_MESSAGE = "Invalid scanner";
    private static final int STOP_GAME_INPUT = -1;

    // Game State
    private final Grid grid;
    private final Scanner scanner;

    // Constructor
    public Game(int rows, int columns, int seedPercentage, Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException(INVALID_SCANNER_EXCEPTION_MESSAGE);
        }
        this.scanner = scanner;
        this.grid = initializeGrid(rows, columns, seedPercentage);
    }

    // Initialize Grid
    private Grid initializeGrid(int rows, int columns, int seedPercentage) {
        if (rows <= 0 || columns <= 0 || seedPercentage < 0 || seedPercentage > 100) {
            throw new InvalidGridException(INVALID_GRID_EXCEPTION_MESSAGE);
        }
        List<List<Cell>> grid = Util.createEmptyGrid(rows, columns);
        Util.seedGrid(grid, seedPercentage);
        return new Grid(grid);
    }

    // Start Game Loop
    public void run() {
        displayGrid();

        int userInput = getUserInput();

        while (userInput != STOP_GAME_INPUT && grid.isAnyCellAlive()) {
            updateGrid();
            displayGrid();
            userInput = getUserInput();
        }

        displayEndMessage(userInput);
        scanner.close();
    }

    // Display the current grid state
    private void displayGrid() {
        System.out.println(CURRENT_GRID_MESSAGE);
        System.out.println(grid);
    }

    // Get user input for next move
    private int getUserInput() {
        System.out.println(STOP_GAME_PROMPT);
        return scanner.nextInt();
    }

    // Update grid to the next generation
    private void updateGrid() {
        grid.nextGenerationUpdate();
    }

    // Display end message based on the reason for stopping
    private void displayEndMessage(int userInput) {
        if (!grid.isAnyCellAlive()) {
            System.out.println(ALL_CELLS_DEAD_MESSAGE);
        } else if (userInput == STOP_GAME_INPUT) {
            System.out.println(GAME_STOPPED_BY_USER_MESSAGE);
        }
    }
}
