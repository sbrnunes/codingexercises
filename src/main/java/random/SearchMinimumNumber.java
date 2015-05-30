package random;

import static org.junit.Assert.assertEquals;

public class SearchMinimumNumber
{

    public static void main(String[] args)
    {
        assertEquals(6, searchMinimumNumber(new int[]{ 3, 4, 5, 6, 7, 8, 1, 2 }));

        assertEquals(5, searchMinimumNumber(new int[]{ 4, 5, 6, 7, 8, 1, 2, 3 }));

        assertEquals(6, searchMinimumNumber(new int[]{ 4, 5, 6, 7, 8, 9, 1, 2, 3 }));

        assertEquals(7, searchMinimumNumber(new int[]{ 2, 3, 4, 5, 6, 7, 8, 1 }));

        assertEquals(1, searchMinimumNumber(new int[]{ 5, 1, 2, 3, 4 }));

        assertEquals(4, searchMinimumNumber(new int[]{ 5, 6, 7, 8, 1, 2, 3, 4 }));

        assertEquals(3, searchMinimumNumber(new int[]{ 5, 6, 7, 1, 2, 3, 4 }));

        assertEquals(0, searchMinimumNumber(new int[]{ 1 }));

        assertEquals(0, searchMinimumNumber(new int[]{ 1, 2 }));

        assertEquals(1, searchMinimumNumber(new int[]{ 2, 1 }));

    }

    private static int searchMinimumNumber(int[] arr)
    {
        return searchMinimumNumber(arr, 0, arr.length - 1);
    }

    private static int searchMinimumNumber(int[] arr, int first, int last)
    {
        int middle = first + (last - first) / 2;

        if(arr[last] < arr[middle])
        {
            return searchMinimumNumber(arr, middle + 1, last);
        }
        else if (arr[last] > arr[middle])
        {
            return searchMinimumNumber(arr, first, middle);
        }
        else
        {
            return first;
        }
    }
}