package bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Just to help remembering bitwise operators
 */
public class TestBitwiseOperations
{
    @Test
    public void testBitwiseOperations() throws Exception
    {
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        /* 12 = 0000 1100 */
        assertEquals(12, c = a & b);

        /* 61 = 0011 1101 */
        assertEquals(61, c = a | b);

        /* 49 = 0011 0001 */
        assertEquals(49, c = a ^ b);

        /* -61 = 1100 0011 */
        assertEquals(-61, c = ~a);

        /* 240 = 1111 0000 */
        assertEquals(240, c = a << 2);

        /* 15 = 0000 1111 */
        assertEquals(15, c = a >> 2);

        /* 1 = 0000 0001 */
        assertEquals(1, c = (a >> 2) & 1);

        /* 15 = 0000 1111 */
        assertEquals(15, c = a >>> 2);
    }
}
