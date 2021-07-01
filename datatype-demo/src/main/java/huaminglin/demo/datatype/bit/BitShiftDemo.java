package huaminglin.demo.datatype.bit;

/*
-2147483648:    10000000000000000000000000000000
<<      0:      0
>>      -1073741824:    11000000000000000000000000000000
>>>     1073741824:     1000000000000000000000000000000

-8:     11111111111111111111111111111000
<<      -16:    11111111111111111111111111110000
>>      -4:     11111111111111111111111111111100
>>>     2147483644:     1111111111111111111111111111100

-1:     11111111111111111111111111111111
<<      -2:     11111111111111111111111111111110
>>      -1:     11111111111111111111111111111111
>>>     2147483647:     1111111111111111111111111111111

8:      1000
<<      16:     10000
>>      4:      100
>>>     4:      100

2147483647:     1111111111111111111111111111111
<<      -2:     11111111111111111111111111111110
>>      1073741823:     111111111111111111111111111111
>>>     1073741823:     111111111111111111111111111111


-1 >> 1: -1

Note: "-1 >> 1" return "-1", which is not as expected. -1/2, 0 is expected.
*/
public class BitShiftDemo {
    public static void main(String[] args) {
        int [] values = {Integer.MIN_VALUE, -8, -1, 8, Integer.MAX_VALUE};
        for (int value : values) {
            System.out.println();
            System.out.println(value + ": \t" + Integer.toBinaryString(value));
            System.out.println("<<\t" + (value << 1) + ": \t" + Integer.toBinaryString(value << 1));
            System.out.println(">>\t" + (value >> 1) + ": \t" + Integer.toBinaryString(value >> 1));
            System.out.println(">>>\t" + (value >>> 1) + ": \t" + Integer.toBinaryString(value >>> 1));
        }
        System.out.println("\n\n-1 >> 1: " + (-1 >> 1));
    }
}
