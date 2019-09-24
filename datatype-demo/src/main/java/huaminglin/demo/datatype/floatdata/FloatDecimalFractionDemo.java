package huaminglin.demo.datatype.floatdata;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/*
54.23f: 54.23
54.23: 54.22999954223633

0.5f can be representated in binary fraction:
0.5
111111000000000000000000000000
0.5

0.1f can't be representated in float binary fraction:
0.1
111101110011001100110011001101
0.10000000149011612
BigDecimal and float 0.1: 0.100000001490116119384765625

0.1 can't be representated in double binary fraction:
0.1
11111110111001100110011001100110011001100110011001100110011010
0.1, In simple terms, the error in converting from decimal to binary is the same as the error when converting from binary to decimal. The errors 'cancel out'.
BigDecimal and double 0.1: 0.1
BigDecimal and double 0.1: 0.1000000000000000055511151231257827021181583404541015625

0.100000000000000005 can't be representated in double binary fraction:
0.1
11111110111001100110011001100110011001100110011001100110011010
0.1

Loose precision through adding:
total += 0.1: 0.1
total += 0.1: 0.2
total += 0.1: 0.30000000000000004
total += 0.1: 0.4
total += 0.1: 0.5
*/
public class FloatDecimalFractionDemo {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(100000);
        df.setRoundingMode(RoundingMode.UNNECESSARY);
        {
            System.out.println("54.23f: " + 54.23f);
            System.out.println("54.23: " + (0.00 + 54.23f));
        }
        {
            System.out.println("\n0.5f can be representated in binary fraction: ");
            float f = 0.5f;
            System.out.println(f);
            System.out.println(Long.toBinaryString(Float.floatToIntBits(f)));
            System.out.println(df.format(f));
        }
        {
            System.out.println("\n0.1f can't be representated in float binary fraction: ");
            float f = 0.1f;
            System.out.println(f);
            System.out.println(Long.toBinaryString(Float.floatToIntBits(f)));
            System.out.println(df.format(f));
            System.out.println("BigDecimal and float 0.1: " + new BigDecimal(0.1f));
        }
        {
            System.out.println("\n0.1 can't be representated in double binary fraction: ");
            double f = 0.1;
            System.out.println(f);
            System.out.println(Long.toBinaryString(Double.doubleToLongBits(f)));
            System.out.println(df.format(f) + ", In simple terms, the error in converting from decimal to binary is the same as the error when converting from binary to decimal. The errors 'cancel out'.");
            System.out.println("BigDecimal and double 0.1: " + new BigDecimal("0.1"));
            System.out.println("BigDecimal and double 0.1: " + new BigDecimal(0.1));
        }
        {
            System.out.println("\n0.100000000000000005 can't be representated in double binary fraction: ");
            double f = 0.100000000000000005;
            System.out.println(f);
            System.out.println(Long.toBinaryString(Double.doubleToLongBits(f)));
            System.out.println(df.format(f));
        }
        {
            System.out.println("\nLoose precision through adding:");
            double total = 0.0;
            for (int i = 0; i < 5; i++) {
                total += 0.1;
                System.out.println("total += 0.1: " + df.format(total));
            }
        }
    }
}
