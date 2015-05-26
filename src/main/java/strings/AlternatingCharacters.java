package strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AlternatingCharacters
{
    public static void main(String[] args) throws IOException
    {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine());

            for(int i = 0; i < n; i++) {

                String str = reader.readLine();

                char[] arr = str.toCharArray();

                int count = 0;
                for(int k=0; k < arr.length - 1; k++) {

                    if(arr[k + 1] == arr[k]) {
                        count++;
                    }
                }

                System.out.println(count);
            }
        }
    }
}
