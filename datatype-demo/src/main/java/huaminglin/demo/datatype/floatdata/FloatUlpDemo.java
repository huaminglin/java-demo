package huaminglin.demo.datatype.floatdata;

import java.math.BigDecimal;

/*
Unit in the last place

Math.nextUp(float)
Step: 0.000000007450580596923828125
0.099999994
0.0999999940395355224609375
Step: 0.000000007450580596923828125
0.1
0.100000001490116119384765625
Step: 0.000000007450580596923828125
0.10000001
0.10000000894069671630859375
Step: 0.000000007450580596923828125
0.10000002
0.100000016391277313232421875
Step: 0.000000007450580596923828125
0.100000024
0.10000002384185791015625

Math.nextUp(double)
Step: 0.00000000000000001387778780781445675529539585113525390625
0.09999999999999999
0.09999999999999999167332731531132594682276248931884765625
Step: 0.00000000000000001387778780781445675529539585113525390625
0.1
0.1000000000000000055511151231257827021181583404541015625
Step: 0.00000000000000001387778780781445675529539585113525390625
0.10000000000000002
0.10000000000000001942890293094023945741355419158935546875

float literal: 0.10000001f vs 0.100000011f
0.10000001f: 0.10000001
0.10000000894069671630859375
0.100000011f: 0.10000001
0.10000000894069671630859375

double literal: 0.10000000149011613 vs 0.10000000149011614
0.10000000149011613: 0.10000000149011613
0.10000000149011613326255343281445675529539585113525390625
0.10000000149011614: 0.10000000149011613
0.10000000149011613326255343281445675529539585113525390625
*/
public class FloatUlpDemo {

  public static void main(String[] args) {
    {
      System.out.println("Math.nextUp(float)");
      float f = Math.nextDown(Math.nextDown(0.1f));
      for (int i = 0; i < 5; i++) {
        float f2 = Math.nextUp(f);
        System.out
            .println("Step: " + new BigDecimal(f2).subtract(new BigDecimal(f)).toPlainString());
        System.out.println(f2);
        System.out.println(new BigDecimal(f2).toPlainString());
        f = f2;
      }
    }
    {
      System.out.println("\nMath.nextUp(double)");
      double f = Math.nextDown(Math.nextDown(0.1));
      for (int i = 0; i < 3; i++) {
        double f2 = Math.nextUp(f);
        System.out
            .println("Step: " + new BigDecimal(f2).subtract(new BigDecimal(f)).toPlainString());
        System.out.println(f2);
        System.out.println(new BigDecimal(f2).toPlainString());
        f = f2;
      }
    }
    System.out.println("\nfloat literal: 0.10000001f vs 0.100000011f");
    {
      float f = 0.10000001f;
      System.out.println("0.10000001f: " + f);
      System.out.println(new BigDecimal(f).toPlainString());
    }
    {
      float f = 0.100000011f;
      System.out.println("0.100000011f: " + f);
      System.out.println(new BigDecimal(f).toPlainString());
    }
    System.out.println("\ndouble literal: 0.10000000149011613 vs 0.10000000149011614");
    {
      double f = 0.10000000149011613;
      System.out.println("0.10000000149011613: " + f);
      System.out.println(new BigDecimal(f).toPlainString());
    }
    {
      double f = 0.10000000149011614;
      System.out.println("0.10000000149011614: " + f);
      System.out.println(new BigDecimal(f).toPlainString());
    }
  }
}
