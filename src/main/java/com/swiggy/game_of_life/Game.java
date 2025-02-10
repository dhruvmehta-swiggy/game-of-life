package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;

import java.util.Scanner;

public class Game {

    private static final String GRID_CANNOT_BE_NULL = "Grid cannot be null";
    private static final String CURRENT_GRID_MESSAGE = "Current Grid:";
    private static final String STOP_GAME_PROMPT = "Enter -1 to stop the game, or any other number to continue:";
    private static final String ALL_CELLS_DEAD_MESSAGE = "All cells are dead. Stopping the game.";
    private static final String GAME_STOPPED_BY_USER_MESSAGE = "Game stopped by user.";
    private static final int STOP_GAME_INPUT = -1;

    public Game(Grid grid) {
        if (grid == null) {
            throw new InvalidGridException(GRID_CANNOT_BE_NULL);
        }
    }

    public void run(Grid grid, Scanner scanner) {
        displayGrid(grid);

        int userInput = getUserInput(scanner);

        while (userInput != STOP_GAME_INPUT && grid.isAnyCellAlive()) {
            updateGrid(grid);
            displayGrid(grid);
            userInput = getUserInput(scanner);
        }

        displayEndMessage(grid, userInput);
        scanner.close();
    }

    private void displayGrid(Grid grid) {
        System.out.println(CURRENT_GRID_MESSAGE);
        System.out.println(grid);
    }

    private int getUserInput(Scanner scanner) {
        System.out.println(STOP_GAME_PROMPT);
        return scanner.nextInt();
    }

    private void updateGrid(Grid grid) {
        grid.nextGenerationUpdate();
    }

    private void displayEndMessage(Grid grid, int userInput) {
        if (!grid.isAnyCellAlive()) {
            System.out.println(ALL_CELLS_DEAD_MESSAGE);
        } else if (userInput == STOP_GAME_INPUT) {
            System.out.println(GAME_STOPPED_BY_USER_MESSAGE);
        }
    }
}
