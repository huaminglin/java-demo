package huaminglin.demo.datatype.bit;

/*
The two's complement of an N-bit number is defined as its complement with respect to 2N; the sum of a number and its two's complement is 2N.
For instance, for the three-bit number 010/2, the two's complement is 110/2, because 010/2 + 110/2 = 1000/2 = 8/10 which is equal to 2^3. The two's complement is calculated by inverting the bits and adding one.

Integer.MAX_VALUE:       2147483647:     1111111111111111111111111111111
Integer.MIN_VALUE:      -2147483648:    10000000000000000000000000000000
-1:	 11111111111111111111111111111111
-2:	 11111111111111111111111111111110
-3:	 11111111111111111111111111111101
-4:	 11111111111111111111111111111100
-5:	 11111111111111111111111111111011
-6:	 11111111111111111111111111111010
-7:	 11111111111111111111111111111001
-8:	 11111111111111111111111111111000
-128:	 11111111111111111111111110000000

Overflow:
Integer.MAX_VALUE + 1:   -2147483648; Integer.MAX_VALUE + 1 == Integer.MIN_VALUE
Integer.MIN_VALUE - 1:  2147483647; Integer.MIN_VALUE - 1 == Integer.MAX_VALUE
Unsigned byte for -128: 128/10000000
Unsigned byte for -1: 255/11111111

Note: -2147483648:    10000000000000000000000000000000;
00000000000000000000000000000000;
So negative representation is one more than positive representation.
Positive representation starts from 0, but negative representation starts from -1.
This is why Integer.MIN_VALUE is -2147483648, not -2147483647.

By checking (-1) - (-8), we can find out that in the negative representation, the role of '0' and '1' is reversed from positive representation;
that is, '0' is the weight bit in the negative representation; more '0' bit, more far from -1.

From "Unsigned byte for -128: 128/10000000" we know that the unsigned byte has the same binary representation as normal byte.

2's Complement works like a clock.
Right half is Clock-wise[0, 2147483647]; Left half is Anti-clock-wise[-1, -2147483648].
If the right half, we count '1' bit; in the left half, we flip the role of '1' bit and '0' bit.
Integer.MAX_VALUE + 1 is Integer.MIN_VALUE; this fits the clock logic.
Integer.MAX_VALUE(it has max '1' bit) is the max in the right half, where '1' matters;
Integer.MIN_VALUE(it has max '0' bit) is the min in the left half, where '0' matters.
*/
public class ComplementDemo {
    public static void main(String[] args) {
        System.out.println("Integer.MAX_VALUE:\t " + Integer.MAX_VALUE + ":\t " + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("Integer.MIN_VALUE:\t" + Integer.MIN_VALUE + ":\t" + Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println("-1:\t " + Integer.toBinaryString(-1));
        System.out.println("-2:\t " + Integer.toBinaryString(-2));
        System.out.println("-3:\t " + Integer.toBinaryString(-3));
        System.out.println("-4:\t " + Integer.toBinaryString(-4));
        System.out.println("-5:\t " + Integer.toBinaryString(-5));
        System.out.println("-6:\t " + Integer.toBinaryString(-6));
        System.out.println("-7:\t " + Integer.toBinaryString(-7));
        System.out.println("-8:\t " + Integer.toBinaryString(-8));
        System.out.println("-128:\t " + Integer.toBinaryString(-128));

        System.out.println("\nOverflow:");
        System.out.println("Integer.MAX_VALUE + 1:\t " + (Integer.MAX_VALUE + 1) + "; Integer.MAX_VALUE + 1 == Integer.MIN_VALUE");
        System.out.println("Integer.MIN_VALUE - 1:\t" + (Integer.MIN_VALUE - 1) + "; Integer.MIN_VALUE - 1 == Integer.MAX_VALUE");

        System.out.println("Unsigned byte for " + Byte.MIN_VALUE  + ": " + Byte.toUnsignedInt(Byte.MIN_VALUE) + '/' + Integer.toBinaryString(Byte.toUnsignedInt(Byte.MIN_VALUE)));
        System.out.println("Unsigned byte for " + (-1) + ": " + Byte.toUnsignedInt((byte)-1) + '/' + Integer.toBinaryString(Byte.toUnsignedInt((byte)-1)));
    }
}

