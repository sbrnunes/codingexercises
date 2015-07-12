package permutation;

import java.util.Arrays;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class ArrayPermutation
{
    public static void main(String[] args)
    {
        print(recursivePermute(new Object[]{ 1, 2, 3, 4 }));

        System.out.println("\n");

        print(iterativePermute(new Object[]{ 1, 2, 3, 4 })); // 1 2 3 4   4 - (1 + 1)
    }

    private static void print(Set<String> permutations)
    {
        permutations.stream().forEach(System.out::println);
    }

    private static Set<String> recursivePermute(Object[] original){
        Set<String> permutations = new TreeSet<>();
        permute(new Object[]{}, original, permutations);
        return permutations;
    }

    private static void permute(Object[] prefix, Object[] suffix, Set<String> permutations){
        int n = suffix.length;

        if (n == 0)
        {
            permutations.add(Arrays.toString(prefix));
        }
        else {
            for (int i = 0; i < n; i++)
            {
                Object[] nextPrefix = Arrays.copyOf(prefix, prefix.length + 1);
                nextPrefix[prefix.length] = suffix[i];

                Object[] nextSuffix = new Object[suffix.length - 1];
                System.arraycopy(suffix, 0, nextSuffix, 0, i);
                System.arraycopy(suffix, i + 1, nextSuffix, i, suffix.length - 1 - i);

                permute(nextPrefix, nextSuffix, permutations);
            }
        }
    }

    private static Set<String> iterativePermute(Object[] original){
        Set<String> permutations = new TreeSet<>();
        permute(original, permutations);
        return permutations;
    }

    private static void permute(Object[] original, Set<String> permutations){

        Stack<Object[]> stack = new Stack<>();

        stack.push(original);

        while(!stack.isEmpty()) {

            original = stack.pop();

            for (int i = 0; i < original.length; i++)
            {
                Object[] permutation = new Object[original.length];
                permutation[0] = original[i];

                System.arraycopy(original, 0, permutation, 1, i);
                System.arraycopy(original, i + 1, permutation, i + 1, original.length - 1 - i);

                if(permutations.add(Arrays.toString(permutation))) {
                    stack.push(permutation);
                }
            }
        }
    }
}
