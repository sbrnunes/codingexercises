package bitmanipulation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CIDRBlock
{
    private static final Pattern addressPattern = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");
    private static final Pattern cidrPattern = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,3})");

    private int address = 0;
    private int network = 0;
    private int broadcast = 0;
    private int prefixSize = 0;
    private int netmask = 0;

    /**
     * Whether the broadcast/network address are included in host count
     */
    private boolean networkAndBroadcastAddressesIncluded = false;

    private CIDRBlock(String cidrBlock)
    {
        calculate(cidrBlock);
    }

    public static CIDRBlock from(String ipv4)
    {
        return new CIDRBlock(ipv4 + "/" + calculatePrefixSize(ipv4));
    }

    public static CIDRBlock from(String ipv4, int prefixSize)
    {
        return new CIDRBlock(ipv4 + "/" + prefixSize);
    }

    public static CIDRBlock fromCIDRNotation(String cidrBlock)
    {
        return new CIDRBlock(cidrBlock);
    }

    public CIDRBlock withNetworkAndBroadcastAddressesIncluded(boolean included)
    {
        this.networkAndBroadcastAddressesIncluded = included;
        return this;
    }

    private void calculate(String cidrBlock)
    {
        assertCidrPattern(cidrBlock);

        String address = getIpv4Address(cidrBlock);
        assertAddress(address);
        int prefixSize = getPrefixSize(cidrBlock);
        rangeCheck(prefixSize, 0, 32);

        this.address = getIpv4AsInt(address);
        this.prefixSize = prefixSize;
        this.netmask = getNetmaskAsInt(prefixSize);
        this.network = this.address & this.netmask; //first address in the range
        this.broadcast = this.network | ~this.netmask; //last address in the range
    }

    /**
     * Returns this CIDR block IPv4 address
     */
    public String getAddress()
    {
        return format(address);
    }

    /**
     * Returns this CIDR block size
     */
    public int getPrefixSize()
    {
        return prefixSize;
    }

    /**
     * Returns the lowest address from the IPv4 Address Range
     */
    public String getLowIpv4Address()
    {
        return format(toArray(getLowAddressAsInt()));
    }

    /**
     * Returns the highest address from the IPv4 Address Range
     */
    public String getHighIpv4Address()
    {
        return format(toArray(getHighAddressAsInt()));
    }

    /**
     * Reurns the netmask
     */
    public String getNetmask()
    {
        return format(netmask);
    }

    /**
     * Utility method to convert a number representing a IPv4 address to binary.
     */
    public static String toBinaryIpv4(int ipv4AsInt)
    {
        String ipv4 = "";
        for (int position = 0; position < 32; position++)
        {
            ipv4 += getBit(ipv4AsInt, position);
        }
        return ipv4;
    }

    /**
     * Checks if the given CIDR block is valid (if the combination of base IPv4 address and block size are valid)
     */
    public static boolean isValid(String cidrNotation)
    {
        assertCidrPattern(cidrNotation);
        return getPrefixSize(cidrNotation) >= calculatePrefixSize(getIpv4Address(cidrNotation));
    }

    /**
     * Check if the given IPv4 address is contained within the range of this CIDR block
     */
    public boolean isIpv4WithinRange(String ipv4)
    {
        assertAddress(ipv4);
        int ipv4AsInt = getIpv4AsInt(ipv4);
        return ipv4AsInt >= getLowAddressAsInt() && ipv4AsInt <= getHighAddressAsInt();
    }

    /**
     * Checks if any of the given IPv4 addresses is contained within the range of this CIDR block
     */
    private boolean isAnyIpv4WithinRange(String... addresses)
    {
        //noinspection SimplifiableIfStatement
        if (addresses == null || addresses.length == 0)
        {
            return false;
        }

        for (String address : addresses)
        {
            if(isIpv4WithinRange(address))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if any of the IPv4 addresses, from the given CIDR block, is contained inside this CIDR block address space OR if any of the
     * addresses from this CIDR block is contained inside the address space of the give CIDR block.
     */
    public boolean clashesWith(CIDRBlock another)
    {
        return isAnyIpv4WithinRange(another.getLowIpv4Address(), another.getHighIpv4Address()) ||
                another.isAnyIpv4WithinRange(getLowIpv4Address(), getHighIpv4Address());
    }

    /**
     * Return the number of IPv4 addresses in the range
     */
    public int getAddressCount()
    {
        int count = this.broadcast - this.network + (networkAndBroadcastAddressesIncluded ? 1 : -1);
        return count < 0 ? 0 : count;
    }

    /**
     * Return all IPv4 addresses
     */
    public String[] getAllAddresses()
    {
        int count = getAddressCount();
        String[] addresses = new String[count];

        if (count == 0)
        {
            return addresses;
        }
        else
        {
            int add = this.getLowAddressAsInt();

            for (int j = 0; add <= this.getHighAddressAsInt(); j++)
            {
                addresses[j] = format(toArray(add));
                add++;
            }

            return addresses;
        }
    }

    public boolean canBeExpandedTo(String cidrNotation)
    {
        assertCidrPattern(cidrNotation);

        if (!getAddress().equals(getIpv4Address(cidrNotation)))
        {
            throw new IllegalArgumentException("Given CIDR block [" + cidrNotation + "] must start with the same IPv4 address as this CIDR block [" + toString() + "]");
        }

        return getPrefixSize(cidrNotation) < getPrefixSize() && isValid(cidrNotation);
    }

    /**
     * Get the lowest address in the range, as an int
     */
    private int getLowAddressAsInt()
    {
        return networkAndBroadcastAddressesIncluded ? this.network : (this.broadcast - this.network > 1 ? this.network + 1 : 0);
    }

    /**
     * Get the highest address in the range, as an int
     */
    private int getHighAddressAsInt()
    {
        return networkAndBroadcastAddressesIncluded ? this.broadcast : (this.broadcast - this.network > 1 ? this.broadcast - 1 : 0);
    }

    /**
     * Returns a String representing a IPv4 address from an array of octets
     */
    private static String format(int[] octets)
    {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < octets.length; ++i)
        {
            str.append(octets[i]);
            if (i != octets.length - 1)
            {
                str.append(".");
            }
        }

        return str.toString();
    }

    /**
     * Returns an array with all the octets of a given IPv4 address, passed as a number.
     */
    private int[] toArray(int ipv4AsInt)
    {
        int[] ret = new int[4];

        for (int j = 3; j >= 0; j--)
        {
            ret[j] |= ipv4AsInt >>> 8 * (3 - j) & 255;
        }

        return ret;
    }

    /**
     * Returns a String representing a IPv4 address from the IPv4 address supplied as an integer
     */
    private static String format(int ipv4AsInt)
    {
        return format(ipParts(ipv4AsInt));
    }

    /**
     * Returns the CIDR prefix size for an IPv4 address.
     * Example: 10.10.1.32 => 00001010 00001010 00000001 00100000 => 32 - 5 = 27
     */
    private static int calculatePrefixSize(String ipv4)
    {
        int ipAsInt = getIpv4AsInt(ipv4);

        //Iterates over all the 32 bits, from the less significant to the most significant
        for (int position = 0; position < 32; position++)
        {
            if (getBit(ipAsInt, position) == 1)
            {
                //Ex: 10.10.1.32 = 00001010000010100000000100100000 => 32 - 5 = 27
                return 32 - position;
            }
        }
        return 0;
    }

    /**
     * Returns the IPv4 address from the given CIDR block
     */
    private static String getIpv4Address(String cidrBlock)
    {
        return cidrBlock.split("/")[0];
    }

    /**
     * Returns the CIDR block size from the given CIDR notation
     */
    private static int getPrefixSize(String cidrBlock)
    {
        return Integer.parseInt(cidrBlock.split("/")[1]);
    }

    /**
     * Returns the IPv4 as an integer
     */
    private static int getIpv4AsInt(String ipv4)
    {
        Matcher matcher = addressPattern.matcher(ipv4);
        if (matcher.matches())
        {
            //Executes bitwise shift lefts by the amount of positions needed to place the '1' bits in the correct position and finally executes a bitwise OR with each octet
            return (ipParts(ipv4)[0] << 24) & 0xFF000000 |
                    (ipParts(ipv4)[1] << 16) & 0xFF0000 |
                    (ipParts(ipv4)[2] << 8) & 0xFF00 |
                    ipParts(ipv4)[3] & 0xFF;
        }
        else
        {
            throw new IllegalArgumentException("Could not parse [" + ipv4 + "]");
        }
    }

    /**
     * Returns the valaue of the bit at the given position, in the given number
     */
    private static byte getBit(int number, int position)
    {
        //Executes a bitwise shift right by <position> positions and a bitwise AND with 1
        //Returns 1 or 0 depending on if the most significant bit is 1 or 0
        return (byte) ((number >> position) & 1);
    }

    /**
     * Retuns an array with all the octets of the given IPv4 address
     */
    private static int[] ipParts(String ipv4)
    {
        String[] parts = ipv4.split("\\.");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])};
    }

    /**
     * Retuns an array with all the octets of the given IPv4 address
     */
    private static int[] ipParts(int ipv4AsInt)
    {
        return new int[]{ipv4AsInt >>> 24, ipv4AsInt >> 16 & 0xff, ipv4AsInt >> 8 & 0xff, ipv4AsInt & 0xff};
    }

    /**
     * Returns the netmask according to the given prefix size
     */
    private static int getNetmaskAsInt(int prefixSize)
    {
        //Applying a shift left bitwise operation by N bits (N = 32 - <netmask_length>)
        //Note that -1 is all '1's (11111111 11111111 11111111 11111111)
        return (-1) << (32 - prefixSize);
    }

    private static void assertAddress(String ipv4)
    {
        Matcher matcher = addressPattern.matcher(ipv4);
        if (!matcher.matches())
        {
            throw new IllegalArgumentException("Could not parse [" + ipv4 + "]");
        }

        String[] ipParts = ipv4.split("\\.");
        for (String value : ipParts)
        {
            rangeCheck(Integer.parseInt(value), 0, 255);
        }
    }

    private static void assertCidrPattern(String cidrBlock)
    {
        Matcher matcher = cidrPattern.matcher(cidrBlock);
        if (!matcher.matches())
        {
            throw new IllegalArgumentException("Could not parse [" + cidrBlock + "]");
        }
    }

    private static void rangeCheck(int value, int begin, int end)
    {
        if (value < begin || value > end)
        {
            throw new IllegalArgumentException("Value [" + value + "] not in range (" + begin + "," + end + "]");
        }
    }

    @Override
    public String toString()
    {
        return getAddress() + "/" + getPrefixSize();
    }
}
