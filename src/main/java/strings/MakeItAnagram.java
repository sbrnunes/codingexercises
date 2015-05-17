package strings;

import java.util.Arrays;
import java.util.Scanner;

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
