package bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.*;

public class CIDRBlockTest
{
    @Test
    public void testFromCIDRNotation() throws Exception
    {
        /*
         * broadcast/network address are not included in host count
         */
        CIDRBlock cidrBlock1 = CIDRBlock.fromCIDRNotation("10.0.3.0/24").withNetworkAndBroadcastAddressesIncluded(false);

        assertEquals("10.0.3.0", cidrBlock1.getAddress());
        assertEquals(254, cidrBlock1.getAddressCount());
        assertEquals("10.0.3.1", cidrBlock1.getLowIpv4Address());
        assertEquals("10.0.3.254", cidrBlock1.getHighIpv4Address());
        assertEquals("255.255.255.0", cidrBlock1.getNetmask());

        String[] addresses1 = cidrBlock1.getAllAddresses();
        assertEquals(254, addresses1.length);
        assertEquals("10.0.3.1", addresses1[0]);
        assertEquals("10.0.3.254", addresses1[addresses1.length - 1]);

        /*
         * broadcast/network address are included in host count
         */
        CIDRBlock cidrBlock2 = CIDRBlock.fromCIDRNotation("10.0.3.0/24").withNetworkAndBroadcastAddressesIncluded(true);

        assertEquals("10.0.3.0", cidrBlock2.getAddress());
        assertEquals(256, cidrBlock2.getAddressCount());
        assertEquals("10.0.3.0", cidrBlock2.getLowIpv4Address());
        assertEquals("10.0.3.255", cidrBlock2.getHighIpv4Address());
        assertEquals("255.255.255.0", cidrBlock2.getNetmask());

        String[] addresses2 = cidrBlock2.getAllAddresses();
        assertEquals(256, addresses2.length);
        assertEquals("10.0.3.0", addresses2[0]);
        assertEquals("10.0.3.255", addresses2[addresses2.length - 1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromCIDRNotationWithInvalidAddress() throws Exception
    {
        CIDRBlock.fromCIDRNotation("10.0.3/24");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromCIDRNotationWithInvalidCidrNotation() throws Exception
    {
        CIDRBlock.fromCIDRNotation("10.0.3.0");
    }

    @Test
    public void testFromIpv4Address() throws Exception
    {
        CIDRBlock cidrBlock = CIDRBlock.from("10.0.3.0").withNetworkAndBroadcastAddressesIncluded(false);

        assertEquals("10.0.3.0", cidrBlock.getAddress());
        assertEquals(24, cidrBlock.getPrefixSize());
        assertEquals(254, cidrBlock.getAddressCount());
        assertEquals("10.0.3.1", cidrBlock.getLowIpv4Address());
        assertEquals("10.0.3.254", cidrBlock.getHighIpv4Address());
        assertEquals("255.255.255.0", cidrBlock.getNetmask());

        String[] addresses = cidrBlock.getAllAddresses();
        assertEquals(254, addresses.length);
        assertEquals("10.0.3.1", addresses[0]);
        assertEquals("10.0.3.254", addresses[addresses.length - 1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIpv4AddressWithInvalidAddress() throws Exception
    {
        CIDRBlock.from("10.0.3");
    }

    @Test
    public void testFromIpv4AndPrefixSize() throws Exception
    {
        CIDRBlock cidrBlock = CIDRBlock.from("10.0.3.0", 24);

        assertEquals("10.0.3.0", cidrBlock.getAddress());
        assertEquals(24, cidrBlock.getPrefixSize());
        assertEquals(254, cidrBlock.getAddressCount());
        assertEquals("10.0.3.1", cidrBlock.getLowIpv4Address());
        assertEquals("10.0.3.254", cidrBlock.getHighIpv4Address());
        assertEquals("255.255.255.0", cidrBlock.getNetmask());

        String[] addresses = cidrBlock.getAllAddresses();
        assertEquals(254, addresses.length);
        assertEquals("10.0.3.1", addresses[0]);
        assertEquals("10.0.3.254", addresses[addresses.length - 1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIpv4AndPrefixSizeWithInvalidAddress() throws Exception
    {
        CIDRBlock.from("10.0.3", 24);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIpv4AndPrefixSizeWithInvalidPrefixSize() throws Exception
    {
        CIDRBlock.from("10.0.3.0", 33);
    }

    @Test
    public void testGetPrefixSize()
    {
        //00001010 00000000 00000011 00000000 => 32 - 8 = 24
        assertEquals(24, CIDRBlock.from("10.0.3.0").getPrefixSize());
        assertEquals(23, CIDRBlock.from("10.0.2.0").getPrefixSize());
        assertEquals(22, CIDRBlock.from("10.0.4.0").getPrefixSize());
        assertEquals(21, CIDRBlock.from("10.0.8.0").getPrefixSize());
    }

    @Test
    public void testIsValid() throws Exception
    {
        //00001010 00000000 00000011 00000000 => 32 - 8 = 24  Mask: 111111111 11111111 11111111 00000000
        assertTrue(CIDRBlock.isValid("10.0.3.0/24"));

        //00001010 00000000 00000011 00000000 => 32 - 8 = 24  Mask: 111111111 11111111 11111110 00000000
        assertFalse(CIDRBlock.isValid("10.0.3.0/23"));

        //00001010 00000000 00000011 00000000 => 32 - 9 = 23  Mask: 111111111 11111111 11111110 00000000
        assertFalse(CIDRBlock.isValid("10.0.3.0/23"));

        //10101100 10001000 01111100 00000000 => 32 - 8 = 24   Mask: 111111111 11111111 11111111 00000000
        assertTrue(CIDRBlock.isValid("172.136.124.0/24"));

        //10101100 10001000 01111100 00000000 => 32 - 9 = 23   Mask: 111111111 11111111 11111110 00000000
        assertTrue(CIDRBlock.isValid("172.136.124.0/23"));

        //10101100 10001000 01111100 00000000 => 32 - 10 = 22  Mask: 111111111 11111111 11111100 00000000
        assertTrue(CIDRBlock.isValid("172.136.124.0/22"));

        //10101100 10001000 01111100 00000000 => 32 - 11 = 21  Mask: 111111111 11111111 11111000 00000000
        assertFalse(CIDRBlock.isValid("172.136.124.0/21"));

        //10101100 10001000 01111100 00000000 => 32 - 12 = 20  Mask: 111111111 11111111 11110000 00000000
        assertFalse(CIDRBlock.isValid("172.136.124.0/20"));

        //10101100 10001000 01111100 00000000 => 32 - 13 = 19  Mask: 111111111 11111111 11100000 00000000
        assertFalse(CIDRBlock.isValid("172.136.124.0/19"));

        //10101100 10001000 01111100 00000000 => 32 - 14 = 18  Mask: 111111111 11111111 11000000 00000000
        assertFalse(CIDRBlock.isValid("172.136.124.0/18"));

        //10101100 10001000 01111100 00000000 => 32 - 15 = 17  Mask: 111111111 11111111 10000000 00000000
        assertFalse(CIDRBlock.isValid("172.136.124.0/17"));

        //10101100 10001000 01111100 00000000 => 32 - 16 = 16  Mask: 111111111 11111111 00000000 00000000
        assertFalse(CIDRBlock.isValid("172.136.124.0/16"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValidWithInvalidIpv4Address()
    {
        CIDRBlock.isValid("10.0.0/24");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValidWithInvalidCidrNotation()
    {
        CIDRBlock.isValid("10.0.0.0");
    }

    @Test
    public void testWithinRange()
    {
        //10.0.3.0/24 => 00001010 00000000 00000011 00000000
        assertBlock("10.0.3.0/24", "10.0.3.1", "10.0.3.254");

        assertFalse(CIDRBlock.fromCIDRNotation("10.0.3.0/24").isIpv4WithinRange("10.0.2.1"));
        assertFalse(CIDRBlock.fromCIDRNotation("10.0.3.0/24").isIpv4WithinRange("10.0.2.254"));
        assertFalse(CIDRBlock.fromCIDRNotation("10.0.3.0/24").isIpv4WithinRange("10.0.4.1"));
        assertFalse(CIDRBlock.fromCIDRNotation("10.0.3.0/24").isIpv4WithinRange("10.0.4.254"));

        //172.136.124.0/24 => 10101100 10001000 01111100 00000000
        assertBlock("172.136.124.0/24", "172.136.124.1", "172.136.124.254");

        //172.136.124.0/23
        assertBlock("172.136.124.0/23", "172.136.124.1", "172.136.125.254");
    }

    private void assertBlock(String cidrBlock, String begin, String end)
    {
        assertTrue(CIDRBlock.fromCIDRNotation(cidrBlock).isIpv4WithinRange(begin));
        assertTrue(CIDRBlock.fromCIDRNotation(cidrBlock).isIpv4WithinRange(end));
    }

    @Test
    public void testNoVlanClash() throws Exception
    {
        CIDRBlock firstVlan = CIDRBlock.fromCIDRNotation("10.0.2.0/24").withNetworkAndBroadcastAddressesIncluded(true);
        CIDRBlock secondVlan = CIDRBlock.fromCIDRNotation("10.0.3.0/24").withNetworkAndBroadcastAddressesIncluded(true);

        assertFalse(firstVlan.clashesWith(secondVlan));
    }

    @Test
    public void testVlanClash_10_0_2_0_23_vs_10_0_3_0_24() throws Exception
    {
        CIDRBlock firstVlan = CIDRBlock.fromCIDRNotation("10.0.2.0/23").withNetworkAndBroadcastAddressesIncluded(true);
        CIDRBlock secondVlan = CIDRBlock.fromCIDRNotation("10.0.3.0/24").withNetworkAndBroadcastAddressesIncluded(true);

        assertTrue(firstVlan.clashesWith(secondVlan));
    }

    @Test
    public void testVlanClash_10_0_0_0_22_vs_10_0_1_0_24() throws Exception
    {
        //Range: 10.0.0.1 -> 10.0.3.254
        CIDRBlock firstVlan = CIDRBlock.fromCIDRNotation("10.0.0.0/22").withNetworkAndBroadcastAddressesIncluded(true);

        //Range: 10.0.1.1 -> 10.0.1.254
        CIDRBlock secondVlan = CIDRBlock.fromCIDRNotation("10.0.1.0/24").withNetworkAndBroadcastAddressesIncluded(true);

        assertTrue(firstVlan.clashesWith(secondVlan));
    }

    @Test
    public void testCanBeExpanded() throws Exception
    {
        //Test expansion options for 10.0.3.0/24
        CIDRBlock cidrBlock = CIDRBlock.fromCIDRNotation("10.0.3.0/24");
        assertFalse(cidrBlock.canBeExpandedTo("10.0.3.0/23"));

        //Test expansion options for 172.136.124.0/24
        cidrBlock = CIDRBlock.fromCIDRNotation("172.136.124.0/24");
        assertTrue(cidrBlock.canBeExpandedTo("172.136.124.0/23"));
        assertTrue(cidrBlock.canBeExpandedTo("172.136.124.0/22"));
        assertFalse(cidrBlock.canBeExpandedTo("172.136.124.0/21"));

        //Test expansion options for 172.136.124.0/23
        cidrBlock = CIDRBlock.fromCIDRNotation("172.136.124.0/23");
        assertFalse(cidrBlock.canBeExpandedTo("172.136.124.0/24")); //testing a smaller address space
        assertTrue(cidrBlock.canBeExpandedTo("172.136.124.0/22"));
        assertFalse(cidrBlock.canBeExpandedTo("172.136.124.0/21"));
    }
}
