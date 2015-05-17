package strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 Problem Statement

 John has discovered various rocks. Each rock is composed of various elements, and each element is represented by a lower-case Latin letter from 'a' to 'z'.
 An element can be present multiple times in a rock. An element is called a gem-element if it occurs at least once in each of the rocks.

 Given the list of N rocks with their compositions, display the number of gem-elements that exist in those rocks.

 Input Format

 The first line consists of an integer, N, the number of rocks.
 Each of the next N lines contains a rock's composition. Each composition consists of lower-case letters of English alphabet.

 Constraints
 1≤N≤100
 Each composition consists of only lower-case Latin letters ('a'-'z').
 1≤ length of each composition ≤100
 Output Format

 Print the number of gem-elements that are common in these rocks. If there are none, print 0.

 Sample input
 3
 abcdde
 baccd
 eeabg

 Sample output
 2

 */
public class Gemstones
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int numRocks = scanner.nextInt();

        scanner.nextLine();

        char[][] rocksComposition = new char[numRocks][];

        for (int i = 0; i < numRocks; i++)
        {
            String composition = scanner.nextLine();

            char[] arr = composition.toCharArray();

            //Sorting the string
            Arrays.sort(arr);

            rocksComposition[i] = arr;
        }

        int[] currentElements = new int[numRocks];

        System.out.println(getNumberOfGems(rocksComposition, currentElements, 0));
    }

    private static int getNumberOfGems(char[][] rocksComposition, int[] currentElements, int gems)
    {

        for (int i = 0; i < rocksComposition.length; i++)
        {
            if(currentElements[i] >= rocksComposition[i].length)
            {
                return gems;
            }

            if(rocksComposition[0][currentElements[0]] < rocksComposition[i][currentElements[i]])
            {
                currentElements[0]++;
                return  getNumberOfGems(rocksComposition, currentElements, gems);
            }
            else if(rocksComposition[0][currentElements[0]] > rocksComposition[i][currentElements[i]])
            {
                currentElements[i]++;
                return  getNumberOfGems(rocksComposition, currentElements, gems);
            }
        }

        gems++; //there was a match

        for (int i = 0; i < currentElements.length; i++)
        {
            currentElements[i]++; //increment all counters
        }

        return  getNumberOfGems(rocksComposition, currentElements, gems);
    }
}
