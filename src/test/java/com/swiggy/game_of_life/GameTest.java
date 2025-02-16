package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    // Utility method to simulate user input
    private Scanner getScannerWithInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        return new Scanner(System.in);
    }

    // Test to verify that the constructor throws an IllegalArgumentException when the scanner is null
    @Test
    public void testConstructor_WhenScannerIsNull_ThenThrowIllegalArgumentException() {
        assertThrows(InvalidGridException.class, () -> new Game(5, 5, 10, null));
    }

    // Test to ensure no exception is thrown when valid parameters are given (rows, columns, seedPercentage, and scanner)
    @Test
    public void testConstructor_WhenValidParametersProvided_ThenNoExceptionThrown() {
        assertDoesNotThrow(() -> new Game(5, 5, 10, new java.util.Scanner(System.in)));
    }

    // Test to verify that the game loop continues when the grid has at least one live cell and the user does not input -1.
    @Test
    public void testRun_WhenUserInputsContinueAndCellsAreAlive_ThenGameContinues() {
        // Simulating user input: 1 (continue) then -1 (stop)
        Scanner scanner = getScannerWithInput("1\n-1\n");
        Game game = new Game(5, 5, 50, scanner);
        assertDoesNotThrow(game::run);
    }

    // Test to verify that the game stops when the user inputs -1 immediately
    @Test
    public void testRun_WhenUserInputsStopImmediately_ThenGameStops() {
        // Simulating user input: -1 (stop immediately)
        Scanner scanner = getScannerWithInput("-1\n");
        Game game = new Game(5, 5, 50, scanner);
        assertDoesNotThrow(game::run);
    }

    // Test to verify that the game stops when all cells are dead
    @Test
    public void testRun_WhenAllCellsAreDead_ThenGameStops() {
        // Using seedPercentage = 0 to make all cells dead initially
        Scanner scanner = getScannerWithInput("1\n");
        Game game = new Game(5, 5, 0, scanner);  // All cells will be dead
        assertDoesNotThrow(game::run);
    }
}
