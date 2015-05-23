package heap;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FindMedian
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int nintegers = scanner.nextInt();

        MedianCalculator medianCalculator = new MedianCalculator();

        for (int i = 0; i < nintegers; i++) { //O(n)
            medianCalculator.addNumber(scanner.nextInt());
            System.out.println(medianCalculator.calculateMedian());
        }
    }

    private static class MedianCalculator {
        Queue<Integer> maxheap = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1)); //ascending order
        Queue<Integer> minheap = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2)); //descending order

        public void addNumber(int number) {

            if(maxheap.isEmpty() || number < maxheap.peek()) {
                maxheap.add(number);
            } else {
                minheap.add(number);
            }

            //balance both sides
            if(maxheap.size() > minheap.size() + 1) {
                minheap.add(maxheap.poll());
            }

            if(minheap.size() > maxheap.size() + 1) {
                maxheap.add(minheap.poll());
            }
        }

        public double calculateMedian()
        {
            if(maxheap.size() == minheap.size()) {
                return (maxheap.peek() + minheap.peek()) / 2.0;
            }
            else {
                return (double) maxheap.size() > minheap.size() ? maxheap.peek() : minheap.peek();
            }
        }
    }
}
