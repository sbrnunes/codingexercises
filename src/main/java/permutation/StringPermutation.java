package permutation;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class StringPermutation
{
    public static void main(String[] args)
    {
        print(recursivePermute("ABCD"));

        System.out.println("\n");

        print(iterativePermute("ABCD"));
    }

    private static void print(Set<String> permutations)
    {
        permutations.stream().forEach(System.out::println);
    }

    private static Set<String> recursivePermute(String str){
        Set<String> permutations = new TreeSet<>();

        permute("", str, permutations);

        return permutations;
    }

    private static void permute(String prefix, String str, Set<String> permutations){
        int n = str.length();

        if (n == 0)
        {
            permutations.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++)
            {
                permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1), permutations);
            }
        }
    }

    private static Set<String> iterativePermute(String str){
        Set<String> permutations = new TreeSet<>();
        permute(str, permutations);
        return permutations;
    }

    private static void permute(String str, Set<String> permutations){

        Stack<String> stack = new Stack<>();

        stack.push(str);

        while(!stack.isEmpty()) {

            str = stack.pop();

            for (int i = 0; i < str.length(); i++)
            {
                String permutation = str.charAt(i) + str.substring(0, i) + str.substring(i + 1);

                if(permutations.add(permutation)) {
                    stack.push(permutation);
                }
            }
        }
    }
}
