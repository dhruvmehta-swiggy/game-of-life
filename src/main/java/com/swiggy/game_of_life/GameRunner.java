package com.swiggy.game_of_life;

import java.util.List;
import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = getGridDimension(scanner, "rows");
        int columns = getGridDimension(scanner, "columns");
        int seedPercentage = getSeedPercentage(scanner);

        List<List<Cell>> initialGrid = initializeGrid(rows, columns, seedPercentage);

        Grid grid = new Grid(initialGrid);
        Game game = new Game(grid);

        game.run(grid, scanner);

        scanner.close();
    }

    private static int getGridDimension(Scanner scanner, String dimensionName) {
        System.out.printf("Enter the number of %s:%n", dimensionName);
        return scanner.nextInt();
    }

    private static int getSeedPercentage(Scanner scanner) {
        System.out.println("Enter the seed percentage (0-100):");
        return scanner.nextInt();
    }

    private static List<List<Cell>> initializeGrid(int rows, int columns, int seedPercentage) {
        List<List<Cell>> grid = Util.createEmptyGrid(rows, columns);
        Util.seedGrid(grid, seedPercentage);
        return grid;
    }


}
