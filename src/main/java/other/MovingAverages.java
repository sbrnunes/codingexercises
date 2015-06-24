package other;

import java.util.Scanner;

public class MovingAverages {

   public static void main(String[] args) {

       Scanner scanner = new Scanner(System.in);

       String numbers = scanner.nextLine();

       String[] strings = numbers.split(" ");

       double[] doubles = new double[strings.length];

       for(int i = 0; i < strings.length; i++) {
           doubles[i] = Double.valueOf(strings[i]);
       }

       int window = scanner.nextInt();

       calculateMovingAverages(doubles, 0, window);
   }


   private static void calculateMovingAverages(double[] arr, int start, int window) { // O(N*M) -> O(N)

       if(arr.length == 0) {
           return;
       }

       if(start == arr.length - 1) {
           System.out.println(arr[start]);
           return;
       }

       if(start + window > arr.length) {
           window = arr.length - start;
       }

       double sum = 0;
       for(int i = start; i < start + window; i++) { //window
           sum += arr[i];
       }

       System.out.print(sum / window + " ");

       calculateMovingAverages(arr, start + 1, window);

   }
}
