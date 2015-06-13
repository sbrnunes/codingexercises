package graphs;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConnectedCellsGrid
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        int[][] grid = new int[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                grid[i][j] = scanner.nextInt();
            }
        }

        findMaxLengthRegion(grid);
    }

    private static void findMaxLengthRegion(int[][] grid)
    {
        int maxLength = 0;
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j] != 0) {
                    maxLength = Math.max(maxLength, dfs(grid, i, j, 0, 2));
                }
            }
        }

        System.out.println(maxLength);
    }

    private static int dfs(int[][] grid, int row, int column, int currentLength, int visitedMarker)
    {
        if(grid[row][column] == 1) {
            currentLength++;
            visitedMarker++;
        }

        grid[row][column] = visitedMarker;

        for (int i = row - 1; i <= row + 1; i++)
        {
            for (int j = column - 1; j <= column + 1; j++)
            {
                if(!(i == row && j == column))
                {
                    if(isWithinGridBounds(grid, i, j) && grid[i][j] == 1)
                    {
                        currentLength = dfs(grid, i, j, currentLength, visitedMarker);
                    }
                }
            }
        }

        return currentLength;
    }

    private static boolean isWithinGridBounds(int[][] grid, int i, int j)
    {
        return i >= 0 & i < grid.length && j >= 0 && j < grid[0].length;
    }
}
