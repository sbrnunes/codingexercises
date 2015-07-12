package strings;

import java.util.*;

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

            if(result.isEmpty())
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
        return permute(original);
    }

    private static String permute(String original){

        String result = "";

        Set<String> permutations = new HashSet<>();

        Stack<String> stack = new Stack<>();

        stack.push(original);

        while(!stack.isEmpty()) {

            String str = stack.pop();

            for (int i = 0; i < str.length(); i++)
            {
                String permutation = str.charAt(i) + str.substring(0, i) + str.substring(i + 1);

                if(permutations.add(permutation)) {
                    stack.push(permutation);

                    if(permutation.compareTo(original) > 0) {
                        if(result.isEmpty() || permutation.compareTo(result) < 0) {
                            result = permutation;
                        }
                    }
                }
            }
        }

        return result;
    }
}
