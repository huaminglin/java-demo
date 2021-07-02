package huaminglin.demo.datatype.bit;

/*
The two's complement of an N-bit number is defined as its complement with respect to 2N; the sum of a number and its two's complement is 2N.
For instance, for the three-bit number 010/2, the two's complement is 110/2, because 010/2 + 110/2 = 1000/2 = 8/10 which is equal to 2^3. The two's complement is calculated by inverting the bits and adding one.

Integer.MAX_VALUE:       2147483647:     1111111111111111111111111111111
Integer.MIN_VALUE:      -2147483648:    10000000000000000000000000000000
-1:      11111111111111111111111111111111

Overflow:
Integer.MAX_VALUE + 1:   -2147483648; Integer.MAX_VALUE + 1 == Integer.MIN_VALUE
Integer.MIN_VALUE - 1:  2147483647; Integer.MIN_VALUE - 1 == Integer.MAX_VALUE

Note: -2147483648:    10000000000000000000000000000000;
00000000000000000000000000000000;
So negative representation is one more than positive representation.
This is why Integer.MIN_VALUE is not -2147483647;

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

        System.out.println("\nOverflow:");
        System.out.println("Integer.MAX_VALUE + 1:\t " + (Integer.MAX_VALUE + 1) + "; Integer.MAX_VALUE + 1 == Integer.MIN_VALUE");
        System.out.println("Integer.MIN_VALUE - 1:\t" + (Integer.MIN_VALUE - 1) + "; Integer.MIN_VALUE - 1 == Integer.MAX_VALUE");
    }
}

