package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class GridChallenge
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testcases = scanner.nextInt();

        for(int i = 0; i < testcases; i++) {
            int size = scanner.nextInt();

            scanner.nextLine();

            char[][] grid = new char[size][size];

            for(int j = 0; j < size; j++) {
                grid[j] = scanner.nextLine().toCharArray();
            }

            System.out.println(canBeLexicographicallySorted(grid) ? "YES" : "NO");
        }
    }

    private static boolean canBeLexicographicallySorted(char[][] grid) { //n^2
        //Each row needs to be sorted, so, start by sorting each row
        for(int i = 0; i < grid.length; i++) { // n
            Arrays.sort(grid[i]); // n log n
        }

        //After sorting row, check column
        for(int c = 0; c < grid.length; c++) { //n
            for(int r = 1; r < grid.length; r++) { //n
                if(grid[r][c] < grid[r-1][c]) {
                    return false;
                }
            }
        }

        return true;
    }
}
