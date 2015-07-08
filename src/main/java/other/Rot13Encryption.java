package other;

public class Rot13Encryption
{
    private static final int ALPHABET_LENGTH = 26;
    private static final int ALPHABET_START = (int) 'a';

    public static void main(String[] args)
    {

        System.out.println(encrypt("abcd", 13));
        // nopq


    }

    private static String encrypt(String input, int numberOfMoves)
    {

        char[] result = input.toCharArray();

        for (int i = 0; i < result.length; i++)
        {

            int encrypted = (int) result[i] + numberOfMoves;

            if (encrypted >= ALPHABET_START + ALPHABET_LENGTH)
            {
                encrypted -= ALPHABET_LENGTH;
            }


            result[i] = (char) encrypted;
        }

        return new String(result);
    }
}
