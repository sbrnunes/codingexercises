package strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TwoStrings
{
    public static void main(String[] args) throws IOException
    {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                int num = Integer.parseInt(reader.readLine());

                for(int i = 0; i < num; i++) {
                    String str1 = reader.readLine();
                    String str2 = reader.readLine();

                    System.out.println(containsSubStr(str1.toCharArray(), str2.toCharArray()) ? "YES" : "NO");
                }
            }
        }

        private static boolean containsSubStr(char[] str1, char[] str2) {
            Arrays.sort(str1);
            Arrays.sort(str2);

            for (int i = 0, j = 0; i < str1.length && j < str2.length;)
            {
                if(str1[i] == str2[j])
                {
                    return true;
                }
                else if(str1[i] < str2[j]) {
                    i++;
                }
                else {
                    j++;
                }
            }

            return false;

        }
}
