package huaminglin.demo.datatype.bit;

/*
Integer.MIN_VALUE
-2147483648:    10000000000000000000000000000000
<<      0:      0
>>      -1073741824:    11000000000000000000000000000000
>>>     1073741824:     1000000000000000000000000000000
~       2147483647:     1111111111111111111111111111111
-       -2147483648:    10000000000000000000000000000000
n & (-n)        -2147483648:    10000000000000000000000000000000
n & (n-1)       0:      0

-8
-8:     11111111111111111111111111111000
<<      -16:    11111111111111111111111111110000
>>      -4:     11111111111111111111111111111100
>>>     2147483644:     1111111111111111111111111111100
~       7:      111
-       8:      1000
n & (-n)        8:      1000
n & (n-1)       -16:    11111111111111111111111111110000

-1
-1:     11111111111111111111111111111111
<<      -2:     11111111111111111111111111111110
>>      -1:     11111111111111111111111111111111
>>>     2147483647:     1111111111111111111111111111111
~       0:      0
-       1:      1
n & (-n)        1:      1
n & (n-1)       -2:     11111111111111111111111111111110

8
8:      1000
<<      16:     10000
>>      4:      100
>>>     4:      100
~       -9:     11111111111111111111111111110111
-       -8:     11111111111111111111111111111000
n & (-n)        8:      1000
n & (n-1)       0:      0

Integer.MAX_VALUE
2147483647:     1111111111111111111111111111111
<<      -2:     11111111111111111111111111111110
>>      1073741823:     111111111111111111111111111111
>>>     1073741823:     111111111111111111111111111111
~       -2147483648:    10000000000000000000000000000000
-       -2147483647:    10000000000000000000000000000001
n & (-n)        1:      1
n & (n-1)       2147483646:     1111111111111111111111111111110


Note: "-1 >> 1" return "-1", which is not as expected. -1/2, 0 is expected.

Note: "~(-1)" return "0".
Note: "~(Integer.MAX_VALUE)" return "Integer.MIN_VALUE".
Note: "~(Integer.MIN_VALUE)" return "Integer.MAX_VALUE".

Note: 2's Complement works like a clock.
"~" works like flip the index from Clock-wise to Anti-clock-wise: ~8 -> -9. (8 - 0) == -(-9 - (-1))

n & (-n): get the rightmost set bit
n & (n-1): Unset the rightmost set bit

Note: (-Integer.MIN_VALUE) is Integer.MIN_VALUE itself;
-Integer.MIN_VALUE=-(-(Integer.MAX_VALUE+1))=Integer.MAX_VALUE+1=Integer.MIN_VALUE
*/
public class BitShiftDemo {
    public static void main(String[] args) {
        int [] values = {Integer.MIN_VALUE, -8, -1, 8, Integer.MAX_VALUE};
        String [] names = {"Integer.MIN_VALUE", "-8", "-1", "8", "Integer.MAX_VALUE"};
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            System.out.println();
            System.out.println(names[i]);
            System.out.println(value + ": \t" + Integer.toBinaryString(value));
            System.out.println("<<\t" + (value << 1) + ": \t" + Integer.toBinaryString(value << 1));
            System.out.println(">>\t" + (value >> 1) + ": \t" + Integer.toBinaryString(value >> 1));
            System.out.println(">>>\t" + (value >>> 1) + ": \t" + Integer.toBinaryString(value >>> 1));
            System.out.println("~\t" + (~value) + ": \t" + Integer.toBinaryString(~value));
            System.out.println("-\t" + (-value) + ": \t" + Integer.toBinaryString(-value));
            System.out.println("n & (-n)\t" + (value & (-value)) + ": \t" + Integer.toBinaryString((value & (-value))));
            System.out.println("n & (n-1)\t" + (value & (value-1)) + ": \t" + Integer.toBinaryString((value & (value-1))));
        }
    }
}
