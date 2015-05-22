package strings;

import java.util.Scanner;

public class BiggerIsGreater
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int testcases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < testcases; i++)
        {
            String str = scanner.nextLine();

            String result = lowestLexicographicallyBiggerPermutation(str);
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

    public static String lowestLexicographicallyBiggerPermutation(String original) {
        return lowestLexicographicallyBiggerPermutation(original, "", original);
    }

    private static String lowestLexicographicallyBiggerPermutation(String original, String current, String suffix) {
        int suffixLength = suffix.length();

        if (suffixLength == 0)
        {
            if(current.compareTo(original) > 0)
            {
                return current;
            }

            return null;
        }
        else {
            String currentLowestPermutation = null;

            for (int i = 0; i < suffixLength; i++)
            {
                String prefix = current + suffix.charAt(i);

                // if permutation is going to be lexicographically lower than original or bigger than the lowest permutation already found
                if(isNotValidPermutation(prefix, original, currentLowestPermutation))
                {
                    continue;
                }

                // call recursive method again, with a new permutation of the suffix
                String permutation = lowestLexicographicallyBiggerPermutation(original, prefix, suffix.substring(0, i) + suffix.substring(i + 1, suffixLength));

                if(currentLowestPermutation == null || isLexicographicallyLower(permutation, currentLowestPermutation)) {
                    currentLowestPermutation = permutation;
                }
            }

            return currentLowestPermutation;
        }
    }

    private static boolean isNotValidPermutation(String prefix, String original, String currentLowestPermutation)
    {
        return prefix.compareTo(original.substring(0, prefix.length())) < 0 ||
                currentLowestPermutation != null && isLexicographicallyLower(currentLowestPermutation.substring(0, prefix.length()), prefix);
    }

    private static boolean isLexicographicallyLower(String permutation1, String permutation2)
    {
        return permutation1 != null && permutation1.compareTo(permutation2) < 0;

    }
}
