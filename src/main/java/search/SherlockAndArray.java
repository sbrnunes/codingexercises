package search;

import java.util.Scanner;

public class SherlockAndArray
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();

        for (int i = 0; i < testCases; i++)
        {
            int nIntegers = scanner.nextInt();

            int[] arr = new int[nIntegers];

            for (int j = 0; j < nIntegers; j++)
            {
                arr[j] = scanner.nextInt();
            }

            boolean result = checkLeftEqualsRight(arr);

            System.out.println(result ? "YES" : "NO");
        }


    }

    private static boolean checkLeftEqualsRight(int[] arr)
    {
        int i = 0;
        int j = arr.length - 1;
        int sumLeft = 0;
        int sumRight = 0;

        while (i < j)
        {
            if(sumLeft < sumRight) {
                sumLeft += arr[i];
                i++;
            }
            else if(sumLeft > sumRight) {
                sumRight += arr[j];
                j--;
            }
            else {
                sumLeft += arr[i];
                sumRight += arr[j];
                i++;
                j--;
            }
        }

        return i == j && sumLeft == sumRight;
    }
}
