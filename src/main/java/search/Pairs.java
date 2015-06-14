package search;

import java.util.Arrays;
import java.util.Scanner;

public class Pairs
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
        {
            arr[i] = scanner.nextInt();
        }

        System.out.println(countPairsWhithDiff(arr, k));
    }

    private static int countPairsWhithDiff(int[] arr, int diff) // 2 * (n log n)
    {
        Arrays.sort(arr); // n log n

        int count = 0;

        for (int i = 0; i < arr.length; i++) //n
        {
            if(i + 1 < arr.length) {
                count += binarySearch(arr, i + 1, arr.length - 1, arr[i] + diff); //log n
            }
        }

        return count;
    }

    private static int binarySearch(int[] arr, int start, int end, int search)
    {
        if(start == end) {
            return arr[start] == search ? 1 : 0;
        }

        int middle = (start + end) / 2;

        if(search <= arr[middle]) {
            return binarySearch(arr, start, middle, search);
        }
        else {
            return binarySearch(arr, middle + 1, end, search);
        }
    }
}
