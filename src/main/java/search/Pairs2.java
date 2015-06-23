package search;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pairs2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Set<Integer> integers = new HashSet<>();

        for (int i = 0; i < n; i++)
        {
            integers.add(scanner.nextInt());
        }

        System.out.println(countPairsWhithDiff(integers, k));
    }

    private static long countPairsWhithDiff(Set<Integer> integers, int diff)
    {
        return integers.stream().filter(integer -> integers.contains(integer + diff)).count();
    }
}
