package com.swiggy.game_of_life;

import com.swiggy.game_of_life.Exceptions.InvalidGridException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GameTest {

    private Game game;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        scanner = mock(Scanner.class);
        game = new Game(1, 1, 0, scanner);
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
}
