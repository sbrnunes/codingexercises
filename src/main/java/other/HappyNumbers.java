package other;

import java.util.HashSet;
import java.util.Set;

public class HappyNumbers {


    public static void main(String[] args) {

        System.out.println(isHappy(19));
        System.out.println(isHappy(4));
    }


    private static boolean isHappy(int number) {

        Set<Integer> accumulator = new HashSet<>();

        if(number == 1) {
            return true;
        }

        accumulator.add(number);

        while(true) {

            String str = "" + number;

            int sum = 0;
            for(int i = 0; i < str.length(); i++) {
                sum += Integer.valueOf("" + str.charAt(i)) * Integer.valueOf("" + str.charAt(i));
            }

            if(accumulator.contains(sum)) {
                return false;
            }

            accumulator.add(sum);

            if(sum == 1) {
                return true;
            } else {
                number = sum;
            }

        }

    }


}
