package strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SherlockAndAnagrams
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int testcases = scanner.nextInt();

        scanner.nextLine();

        for(int i = 0; i < testcases; i++) {

            String str = scanner.nextLine();

            long n = countAnagramicPairs(str);
            System.out.println(n);
        }
    }

    private static long countAnagramicPairs(String str) { // n^3
        Map<String, Integer> anagrams = new HashMap<>();

        for (int from = 0; from < str.length(); from++) { //n

            for (int to = from + 1; to <= str.length(); to++) { // n

                String substr = sort(str.substring(from, to)); // n log n

                System.out.println(str.substring(from, to));

                if(anagrams.containsKey(substr)) {
                    anagrams.put(substr, anagrams.get(substr) + 1);
                }
                else {
                    anagrams.put(substr, 1);
                }
            }
        }

        int result = 0;
        for (Integer integer : anagrams.values())
        {
            result += integer * (integer - 1) / 2;
        }

        return result;
    }

    private static String sort(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
