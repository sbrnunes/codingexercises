package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MinimumAverageWaitingTime
{
    public static void main(String[] args) {

        Queue<Customer> orders = new PriorityQueue<>(Customer.Order.TIME_OF_ORDER); //first order by time of order

        Scanner scanner = new Scanner(System.in);
        int ncustomers = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < ncustomers; i++) {
            long timeOfOrder = scanner.nextLong();
            long timeToCook = scanner.nextLong();

            Customer customer = new Customer(timeOfOrder, timeToCook);
            orders.add(customer);
        }

        Customer first = orders.poll();

        Queue<Customer> waitingList = new PriorityQueue<>(Customer.Order.TIME_TO_COOK); // then order by waiting time
        waitingList.add(first);

        long time = first.timeOfOrder;
        long waitingTime = 0;
        while(!waitingList.isEmpty()) {
            Customer next = waitingList.poll();
            time += next.timeToCook;
            waitingTime += time - next.timeOfOrder;

            while (!orders.isEmpty() && orders.peek().timeOfOrder <= time) { //cook does not know about future orders

                Customer customer = orders.poll();
                waitingList.add(customer);
            }
        }

        System.out.println(waitingTime / ncustomers);
    }

    private static class Customer {
        long timeOfOrder;
        long timeToCook;

        public Customer(long timeOfOrder, long timeToCook) {
            this.timeOfOrder = timeOfOrder;
            this.timeToCook = timeToCook;
        }

        private enum Order implements Comparator<Customer> {
            TIME_OF_ORDER
                    {
                        @Override
                        public int compare(Customer o1, Customer o2)
                        {
                            return Long.valueOf(o1.timeOfOrder).compareTo(o2.timeOfOrder);
                        }
                    },
            TIME_TO_COOK
                    {
                        @Override
                        public int compare(Customer o1, Customer o2)
                        {
                            return Long.valueOf(o1.timeToCook).compareTo(o2.timeToCook);
                        }
                    };
        }
    }
}
