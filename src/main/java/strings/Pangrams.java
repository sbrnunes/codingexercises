package strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Pangrams
{
    public static void main(String[] args) throws IOException
    {
        Set<Character> letters = new HashSet<>();
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();
            char[] arr = str.toCharArray();
            for(char ch : arr) {
                if(Character.isAlphabetic(ch))
                {
                    letters.add(Character.toLowerCase(ch));
                }
            }
        }

        System.out.println(letters.size() == 26 ? "pangram" : "not pangram");
    }
}
