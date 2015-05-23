package heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FindMedian
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int nintegers = scanner.nextInt();

        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < nintegers; i++) { //O(n)
            queue.add(scanner.nextInt());
            printMedian(queue);               //O(n * n log(n)) => O(n^2 log(n))
        }
    }

    private static void printMedian(Queue<Integer> integers) {

        if(integers.size() == 1) {
            System.out.println((double) integers.peek());
            return;
        }

        Integer[] arr = integers.toArray(new Integer[integers.size()]);

        Arrays.sort(arr); //O(n log(n))

        int center = arr.length / 2;

        if(arr.length % 2 == 0) {
            System.out.println((arr[center - 1] + arr[center]) / 2.0);
        } else {
            System.out.println((double)arr[center]);
        }
    }
}
