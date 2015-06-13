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
            System.out.println(findMaximumSubarray(integers, 0, size - 1, modulo));
        }
    }

    //Kadane’s Algorithm:
    private static long maxSoFar(long[] arr, long modulo)
    {
        long maxSoFar = 0;
        long maxEndingHere = 0;

        for (long current : arr)
        {
            maxEndingHere = (maxEndingHere + current) % modulo;

            if (maxEndingHere < 0)
            {
                maxEndingHere = 0;
            }

            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    //Divide and Conquer algorithm from Cormen
    private static long findMaximumSubarray(long[] arr, int low, int high, long modulo) {

        if(low == high) {
            return arr[low];
        }
        else {

            int middle = (low + high) / 2;

            long leftSum = findMaximumSubarray(arr, low, middle, modulo);
            long rightSum = findMaximumSubarray(arr, middle + 1, high, modulo);
            long crossSum = findMaximumCrossingSubarray(arr, low, middle, high, modulo);

            if(leftSum % modulo >= rightSum % modulo && leftSum % modulo >= crossSum % modulo) {
                return leftSum % modulo;
            }
            else if(rightSum % modulo >= leftSum % modulo && rightSum % modulo >= crossSum % modulo) {
                return rightSum % modulo;
            }
            else {
                return crossSum % modulo;
            }
        }
    }

    private static long findMaximumCrossingSubarray(long[] arr, int low, int middle, int high, long modulo) {
        long leftSum = Long.MIN_VALUE;
        long sum = 0;

        for(int i = middle; i >= low; i-- ) {
            sum += arr[i];

            if(sum > leftSum) {
                leftSum = sum;
            }
        }

        long rightSum = Long.MIN_VALUE;
        sum = 0;

        for(int j = middle + 1; j <= high; j++ ) {
            sum += arr[j];

            if(sum > rightSum) {
                rightSum = sum;
            }
        }

        return leftSum + rightSum;
    }
}
