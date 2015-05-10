package graphs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberOfIslandsTest
{

    @Test
    public void testCountNumberOfIslandsIsland() throws Exception
    {
        assertEquals(Integer.valueOf(1), Integer.valueOf(NumberOfIslands.countNumberOfIslands(new int[][]{
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        })));

        assertEquals(Integer.valueOf(1), Integer.valueOf(NumberOfIslands.countNumberOfIslands(new int[][]{
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        })));

        assertEquals(Integer.valueOf(1), Integer.valueOf(NumberOfIslands.countNumberOfIslands(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1}
        })));

        assertEquals(Integer.valueOf(1), Integer.valueOf(NumberOfIslands.countNumberOfIslands(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0}
        })));

        assertEquals(Integer.valueOf(1), Integer.valueOf(NumberOfIslands.countNumberOfIslands(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        })));

        assertEquals(Integer.valueOf(1), Integer.valueOf(NumberOfIslands.countNumberOfIslands(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        })));

        assertEquals(Integer.valueOf(1), Integer.valueOf(NumberOfIslands.countNumberOfIslands(new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0}
        })));

        assertEquals(Integer.valueOf(5), Integer.valueOf(NumberOfIslands.countNumberOfIslands(new int[][]{
                {1, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 1, 1}
        })));

    }
}