package search;

import java.util.Scanner;

public class MaximiseSum
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();

        for (int i = 0; i < testCases; i++)
        {
            int size = scanner.nextInt();
            long modulo = scanner.nextLong();

            long[] integers = new long[size];

            for (int j = 0; j < integers.length; j++)
            {
                integers[j] = scanner.nextLong();
            }

            System.out.println(maxSoFar(integers, modulo));
        }
    }

    //Kadane’s Algorithm:
    private static long maxSoFar(long[] arr, long modulo)
    {
        long max_so_far = 0;
        long max_ending_here = 0;


        for (int i = 0; i < arr.length; i++) {
            max_ending_here = (max_ending_here + arr[i]) % modulo;

            if(max_ending_here < 0) {
                max_ending_here = 0;
            }

            if(max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
            }
        }

        return max_so_far;
    }
}
