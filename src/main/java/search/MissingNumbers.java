package search;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MissingNumbers
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int sizeFirstList = scanner.nextInt();

        Map<Integer, Integer> firstListNumberFreqs = new TreeMap<>();
        for (int i = 0; i < sizeFirstList; i++)
        {
            int number = scanner.nextInt();

            if(firstListNumberFreqs.containsKey(number)) {
                firstListNumberFreqs.put(number, firstListNumberFreqs.get(number) + 1);
            }
            else {
                firstListNumberFreqs.put(number, 1);
            }
        }

        int sizeSecondList = scanner.nextInt();

        Map<Integer, Integer> secondListNumberFreqs = new TreeMap<>(); //red black tree
        for (int i = 0; i < sizeSecondList; i++)
        {
            int number = scanner.nextInt();

            if(secondListNumberFreqs.containsKey(number)) { //log n
                secondListNumberFreqs.put(number, secondListNumberFreqs.get(number) + 1); //log n
            }
            else {
                secondListNumberFreqs.put(number, 1); //log n
            }

        }

        for (Map.Entry<Integer,Integer> entry : secondListNumberFreqs.entrySet())
        {
            if(!firstListNumberFreqs.containsKey(entry.getKey())) {
                System.out.print(entry.getKey() + " ");
            }
            else if(firstListNumberFreqs.get(entry.getKey()).intValue() != entry.getValue().intValue()) {
                System.out.print(entry.getKey() + " ");
            }
        }
    }
}
