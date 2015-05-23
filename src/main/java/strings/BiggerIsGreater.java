package strings;

import java.util.Scanner;
import java.util.TreeSet;

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
        TreeSet<String> permutations = new TreeSet<>();
        permutations.add(original);

        recursivePermute("", original, permutations);

        return permutations.higher(original);
    }

    private static void recursivePermute(String prefix, String suffix, TreeSet<String> permutations) {

        if(suffix.isEmpty()) {
            if(prefix.compareTo(permutations.first()) > 0)
            {
                permutations.add(prefix);
            }
        }
        else {
            for (int i = 0; i < suffix.length(); i++)
            {
                recursivePermute(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i + 1), permutations);
            }
        }
    }
}
