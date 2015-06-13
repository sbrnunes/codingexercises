package search;

import java.util.Scanner;

public class TheGridSearch
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();

        for (int i = 0; i < testCases; i++)
        {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();

            scanner.nextLine();

            char[][] grid = new char[rows][columns];

            for (int r = 0; r < rows; r++)
            {
                grid[r] = scanner.nextLine().toCharArray();
            }

            int patternRows = scanner.nextInt();
            int patternColumns = scanner.nextInt();

            scanner.nextLine();

            char[][] pattern = new char[patternRows][patternColumns];

            for (int pr = 0; pr < patternRows; pr++)
            {
                String line = scanner.nextLine();

                pattern[pr] = line.toCharArray();
            }

            boolean found = checkPatternExistsInGrid(grid, pattern);
            System.out.println(found ? "YES" : "NO");
        }
    }

    private static boolean checkPatternExistsInGrid(char[][] grid, char[][] pattern)
    {
        for (int row = 0; row < grid.length; row++)
        {
            for (int column = 0; column < grid[row].length; column++)
            {
                if(grid[row][column] == pattern[0][0]) {
                    if(checkPattern(grid, pattern, row, column)) {
                      return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkPattern(char[][] grid, char[][] pattern, int row, int column)
    {
        for (int r = row, pr = 0; pr < pattern.length; r++, pr++)
        {
            if(r >= grid.length) {
                return false; //pattern was not matched entirely
            }

            for (int c = column, pc = 0; pc < pattern[pr].length; c++, pc++) {

                if(c >= grid[r].length) {
                    return false;  //pattern was not matched entirely
                }

                if(grid[r][c] != pattern[pr][pc]) {
                    return false;
                }
            }
        }

        return true;
    }
}
