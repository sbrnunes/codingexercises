package strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FunnyString
{
    public static void main(String[] args) throws IOException
    {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            int n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++) {
                String str = reader.readLine();
                if(isFunny(str)) {
                    System.out.println("Funny");
                }
                else {
                    System.out.println("Not Funny");
                }
            }
        }
    }

    private static boolean isFunny(String str)
    {
        char[] arr = str.toCharArray();

        for (int i = 1, j = arr.length - 2; i < arr.length; i++, j--)
        {
            if(abs(ascii(arr[i]) - ascii(arr[i - 1])) != abs(ascii(arr[j]) - ascii(arr[j + 1])))
            {
                   return false;
            }
        }

        return true;
    }

    private static int ascii(char ch) {
        return (int)ch;
    }

    private static int abs(int num) {
        return Math.abs(num);
    }
}
