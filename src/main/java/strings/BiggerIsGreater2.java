package strings;

import java.util.Arrays;
import java.util.Scanner;

public class BiggerIsGreater2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int testcases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < testcases; i++)
        {
            String str = scanner.nextLine();

            String result = lowestPermutationBiggerThan(str);

            if(result == null)
            {
                System.out.println("no answer");
            }
            else
            {
                System.out.println(result);
            }
        }
    }

    public static String lowestPermutationBiggerThan(String original) {

        char[] word = original.toCharArray();

        int start = -1;
        for (int i = 0; i < word.length - 1; i++)
        {
            //looking for the last character in the string that is lexicographically smaller than the character that follows
            if(word[i] < word[i + 1]) {
                start = i;
            }
        }

        if (start < 0){
            return null;
        }

        int end = -1;

        for (int j = start + 1; j < word.length; j++)
        {
            //starting from the index above, looking for the last character in the string that is lexicographically bigger than the character at the starting index
            //in the code above, we ensured this character is going to be the lexicographically smallest of all the potential ones
            if(word[start] < word[j]) {
                end = j;
            }
        }

        //swapping the start and end position
        char aux = word[start];
        word[start] = word[end];
        word[end] = aux;

        //after sorting from start + 1 to the end of the string, the final string will be the lexicographically smallest one
        Arrays.sort(word, start + 1, word.length);

        return new String(word);
    }
}
