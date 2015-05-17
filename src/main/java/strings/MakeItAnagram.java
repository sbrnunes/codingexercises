package strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 Alice recently started learning about cryptography and found that anagrams are very useful. Two strings are anagrams of each other if they have
 same character set. For example strings "bacdc" and "dcbac" are anagrams, while strings "bacdc" and "dcbad" are not.

 Alice decides on an encryption scheme involving 2 large strings where encryption is dependent on the minimum number of character deletions required
 to make the two strings anagrams. She need your help in finding out this number.

 Given two strings (they can be of same or different length) help her in finding out the minimum number of character deletions required to make two
 strings anagrams. Any characters can be deleted from any of the strings.

 Input Format
 Two lines each containing a string.

 Constraints
 1 <= Length of A,B <= 10000
 A and B will only consist of lowercase latin letter.

 Output Format
 A single integer which is the number of character deletions.

 Sample Input:
 cde
 abc

 Sample Output:
 4

 */
public class MakeItAnagram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        int deleted = makeItAnagram(str1, str2);

        System.out.println(deleted);
    }

    private static int makeItAnagram(String str1, String str2) {

        char[] arr1 = str1.toCharArray();
        Arrays.sort(arr1);

        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr2);

        int i = 0;
        int j = 0;
        int deleted = 0;
        while (i < arr1.length || j < arr2.length) {
            if(i >= arr1.length) {
                return deleted + arr2.length - j;
            }
            else if(j >= arr2.length) {
                return deleted + arr1.length - i;
            }
            else {
                //If diferent, increment deleted counter
                if(arr1[i] != arr2[j]){
                    deleted++;
                }

                if(arr1[i] < arr2[j]){
                    i++;
                } else if(arr1[i] > arr2[j]){
                    j++;
                } else {
                    //There was a match. Increment both.
                    i++;
                    j++;
                }
            }
        }

        return deleted;
    }
}
