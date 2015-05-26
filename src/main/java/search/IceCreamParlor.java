package search;

import java.util.Arrays;
import java.util.Scanner;

public class IceCreamParlor
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();

        for (int i = 0; i < testCases; i++)
        {
            int exactAmount = scanner.nextInt();
            int nflavours = scanner.nextInt();

            FlavourPrice[] cflavours = new FlavourPrice[nflavours];

            for (int j = 0; j < nflavours; j++)
            {
                cflavours[j] = new FlavourPrice(j + 1, scanner.nextInt());
            }

            find2Flavours(cflavours, exactAmount);
        }
    }

    private static void find2Flavours(FlavourPrice[] cflavours, int exactAmount)
    {
        Arrays.sort(cflavours);

        int i = 0;
        while( i < cflavours.length ) {

            int j = cflavours.length - 1;
            while ( j >= i ) {

                // We got a match! All done.
                if (cflavours[i].price + cflavours[j].price == exactAmount)
                {
                    print(cflavours[i], cflavours[j]);
                    return;
                }
                if (cflavours[i].price + cflavours[j].price > exactAmount) {
                    //If the sum was too big, decrement j.
                    j--;
                }
                else {
                    //If the sum was too small, increment i.
                    i++;
                }
            }
        }
    }

    private static void print(FlavourPrice cflavour1, FlavourPrice cflavour2)
    {
        if(cflavour1.flavour < cflavour2.flavour)
        {
            System.out.println((cflavour1.flavour) + " " + (cflavour2.flavour));
        }
        else {
            System.out.println((cflavour2.flavour) + " " + (cflavour1.flavour));
        }
    }

    private static class FlavourPrice implements Comparable<FlavourPrice> {

        private final int flavour;
        private final int price;

        public FlavourPrice(int flavour, int price)
        {
            this.flavour = flavour;
            this.price = price;
        }

        @Override
        public int compareTo(FlavourPrice o)
        {
            return Integer.valueOf(this.price).compareTo(o.price);
        }
    }
}
