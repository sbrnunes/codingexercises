package graphs;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a 2-d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is
 * formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 */
public class NumberOfIslands
{
    public static int countNumberOfIslands(int[][] grid)
    {
        int count = 0;

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                GridNode start = new GridNode(i, j);

                Stack<GridNode> stack = new Stack<>();
                stack.push(start);

                if (start.isLand(grid)) //land
                {
                    count++;
                }

                dfs(stack, grid);
            }
        }

        return count;
    }

    private static void dfs(Stack<GridNode> stack, int[][] grid)
    {
        if (stack.isEmpty())
        {
            return;
        }

        GridNode node = stack.pop();

        if (node.isVisited(grid) || node.isWater(grid))
        {
            return;
        }

        node.markAsVisited(grid);

        node.getChildren(grid).forEach(child -> {
            stack.push(child);
            dfs(stack, grid);
        });
    }

    private static class GridNode
    {
        private final int row;
        private final int column;

        GridNode(int row, int column)
        {
            this.row = row;
            this.column = column;
        }

        public boolean isVisited(int[][] grid)
        {
            return grid[row][column] == 2;
        }

        public boolean isWater(int[][] grid)
        {
            return grid[row][column] == 0;
        }

        public boolean isLand(int[][] grid)
        {
            return grid[row][column] == 1;
        }

        public void markAsVisited(int[][] grid)
        {
            grid[row][column] = 2;
        }

        public List<GridNode> getChildren(int[][] grid)
        {
            List<GridNode> children = new ArrayList<>(2);

            if (row + 1 < grid.length)
            {
                children.add(new GridNode(row + 1, column));
            }

            if (column + 1 < grid[row].length)
            {
                children.add(new GridNode(row, column + 1));
            }

            return children;
        }
    }
}
