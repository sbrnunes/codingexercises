package strings;

import java.util.Arrays;

public class CountOdds
{
    public static void main(String[] args) {

        int number = getOddNumber(new int[]{1,1,2,2 });

        System.out.println(number);
    }

    private static int getOddNumber(int[] arr) {
        Arrays.sort(arr); // n log(n)

        int count = 0;

        for(int i = 0; i < arr.length; i++) { //n

            if(count == arr.length) {
                return -1;
            }

            if(i == arr.length - 1) {
                return arr[i];
            }

            if(arr[i] != arr[i+1]) {
                if(count % 2 != 0) {
                    return arr[i];
                }
            }

            count = count + 2;
        }

        return -1;
    }


}
