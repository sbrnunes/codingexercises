package strings;

import java.util.Scanner;

public class PalindromeIndex
{
    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < testCases; i++)
        {

            String word = scanner.nextLine();

            System.out.println(getRemoveIndexToMakePalindrome(word));
        }
    }

    private static int getRemoveIndexToMakePalindrome(String word)
    {
        int middle = word.length() / 2;

        int removed = 0;
        int current = -1;
        for (int i = 0, j = word.length() - 1; i <= middle && j >= middle; i++, j--)
        {
            if (word.charAt(i) != word.charAt(j))
            {
                if(word.charAt(i+1) == word.charAt(j) && word.charAt(i) == word.charAt(j-1)) {
                    //Look forward for next match
                    if(word.charAt(i+2) != word.charAt(j-1)) {
                        current = j;
                        j--;
                    }
                    else if(word.charAt(i+1) != word.charAt(j-2)){
                        current = i;
                        i++;
                    }
                }
                else if(word.charAt(i+1) == word.charAt(j)) {
                    current = i; //remove at i
                    i++;
                }
                else if (word.charAt(i) == word.charAt(j-1)) {
                    current = j; //remove at j
                    j--;
                }

                removed++;

                if(removed > 1) {
                    return -1;
                }
            }
        }

        return removed == 0 ? -1 : current;
    }

}
