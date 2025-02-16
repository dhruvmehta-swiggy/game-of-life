package com.swiggy.game_of_life;

import java.util.Scanner;

public class User {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect user inputs
        int rows = getGridDimension(scanner, "rows");
        int columns = getGridDimension(scanner, "columns");
        int seedPercentage = getSeedPercentage(scanner);

        // Create and run the game
        Game game = new Game(rows, columns, seedPercentage, scanner);
        game.run();
    }

    // Get grid dimensions from the user
    private static int getGridDimension(Scanner scanner, String dimensionName) {
        System.out.printf("Enter the number of %s:%n", dimensionName);
        return scanner.nextInt();
    }

    // Get seed percentage from the user
    private static int getSeedPercentage(Scanner scanner) {
        System.out.println("Enter the seed percentage (0-100):");
        return scanner.nextInt();
    }
}