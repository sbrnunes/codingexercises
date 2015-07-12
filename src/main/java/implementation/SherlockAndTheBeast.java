package implementation;

import java.util.Scanner;

public class SherlockAndTheBeast
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int i = 0; i < testcases; i++) {
           int n = scanner.nextInt();
            System.out.println(findLargestDecentNumber(n));
        }
    }

    private static String findLargestDecentNumber(int n) {
        if(n < 3) {
            return "-1";
        }

        char[] result = new char[n];

        for(int i = 0; i < n; i++) {
            result[i] = '5';
        }

        if(n % 3 == 0) {
            return new String(result);
        }

        int t = 1;
        while(t <= n) {
            result[n - t] = '3';

            if(t % 5 == 0 && (n - t) % 3 == 0) {
                return new String(result);
            }

            t++;
        }

        return "-1";
    }
}