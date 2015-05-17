package strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 Problem Statement

 Sid is obsessed with reading short stories. Being a CS student, he is doing some interesting frequency analysis with the books.
 He chooses strings S1 and S2 in such a way that |len(S1)−len(S2)|≤1.

 Your task is to help him find the minimum number of characters of the first string he needs to change to enable him to make it an anagram of the second string.

 Note: A word x is an anagram of another word y if we can produce y by rearranging the letters of x.

 Input Format The first line will contain an integer, T, representing the number of test cases. Each test case will contain a string having length len(S1)+len(S2),
 which will be concatenation of both the strings described above in the problem.The given string will contain only characters from a to z.

 Output Format An integer corresponding to each test case is printed in a different line, i.e. the number of changes required for each test case. Print −1 if it is not possible.

 Sample Input

 6
 aaabbb
 ab
 abc
 mnop
 xyyx
 xaxbbbxx
 Sample Output

 3
 1
 -1
 2
 0
 1
 */
public class Anagram
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();
        scanner.nextLine();

        for(int t = 0; t < testCases; t++) {
            String str = scanner.nextLine();
            String str1 = str.substring(0, str.length() / 2); //O(n) after Java 7u6, O(1) before Java 7u6
            String str2 = str.substring(str.length() / 2, str.length()); //O(n), as mentioned above
            System.out.println(countNumChangesToMakeAnagram(str1, str2));
        }
    }

    private static int countNumChangesToMakeAnagram(String str1, String str2) {
        if(str1.length() != str2.length()) {
            return -1;
        }

        char[] arr1 = str1.toCharArray(); //O(n)
        Arrays.sort(arr1); //O(nlogn)

        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr2); //O(nlogn)

        int matches = 0;
        int i = 0;
        int j = 0;
        while(i < arr1.length && j < arr2.length) { //O(n)
            if(arr1[i] == arr2[j]) {
                matches++;
            }

            if(arr1[i] < arr2[j]) {
                i++;
            }
            else if(arr1[i] > arr2[j]) {
                j++;
            }
            else {
                i++;
                j++;
            }
        }

        return str1.length() - matches;
    }
}
