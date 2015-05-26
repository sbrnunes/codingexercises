package strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GameOfThrones
{
    public static void main(String[] args) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String str = reader.readLine();
                System.out.println(isAnagramOfPalindrome(str) ? "YES" : "NO");
            }
    }

    private static boolean isAnagramOfPalindrome(String str) {
        char[] arr = str.toCharArray();

        Arrays.sort(arr);

        int odds = 0;
        for (int i = 0; i < arr.length - 1; i = i + 2)
        {
            if(arr[i] != arr[i+1]) {
                odds++;
            }

            if(arr.length % 2 == 0 && odds > 0){
                return false;
            }
            else if(arr.length % 2 != 0 && odds > 1) {
                return false;
            }
        }

        return true;
    }
}
