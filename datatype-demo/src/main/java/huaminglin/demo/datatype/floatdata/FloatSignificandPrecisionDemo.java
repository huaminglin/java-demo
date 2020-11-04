package huaminglin.demo.datatype.floatdata;

/*
Start to check precision lose for float and int:
33554433(2000001) -> 33554432(2000000)
33554434(2000002) -> 33554432(2000000)
33554435(2000003) -> 33554436(2000004)
33554437(2000005) -> 33554436(2000004)
33554438(2000006) -> 33554440(2000008)
Precision lose found for float and int: 5

Start to check precision lose for double and int:
Precision lose found for double and int: 0

Start to check precision lose for double and long:
18014398509481985(40000000000001) -> 18014398509481984(40000000000000)
18014398509481986(40000000000002) -> 18014398509481984(40000000000000)
18014398509481987(40000000000003) -> 18014398509481988(40000000000004)
18014398509481989(40000000000005) -> 18014398509481988(40000000000004)
18014398509481990(40000000000006) -> 18014398509481992(40000000000008)
Precision lose found for double and long: 5
*/
public class FloatSignificandPrecisionDemo {

  private static void printFloatIntPrecision(int start) {
    System.out.println("\nStart to check precision lose for float and int: ");
    int found = 0;
    for (int i = start; i < Integer.MAX_VALUE; i++) {
      float f = i;
      int fi = (int) f;
      if (fi != i) {
        System.out.println(
            i + "(" + Integer.toHexString(i) + ") -> " + fi + "(" + Long.toHexString(fi) + ")");
        found += 1;
        if (found >= 5) {
          break;
        }
      }
    }
    System.out.println("Precision lose found for float and int: " + found);
  }

  private static void printDoubleIntPrecision(int start) {
    System.out.println("\nStart to check precision lose for double and int: ");
    int found = 0;
    for (int i = start; i < Integer.MAX_VALUE; i++) {
      double f = i;
      int fi = (int) f;
      if (fi != i) {
        System.out.println(
            i + "(" + Integer.toHexString(i) + ") -> " + fi + "(" + Long.toHexString(fi) + ")");
        found += 1;
        if (found >= 5) {
          break;
        }
      }
    }
    System.out.println("Precision lose found for double and int: " + found);
  }

  private static void printDoubleLongPrecision(long start) {
    System.out.println("\nStart to check precision lose for double and long: ");
    int found = 0;
    for (long i = start; i < Long.MAX_VALUE; i++) {
      double f = i;
      long fi = (long) f;
      if (fi != i) {
        System.out.println(
            i + "(" + Long.toHexString(i) + ") -> " + fi + "(" + Long.toHexString(fi) + ")");
        found += 1;
        if (found >= 5) {
          break;
        }
      }
    }
    System.out.println("Precision lose found for double and long: " + found);
  }

  public static void main(String[] args) {
    // Float significand precision: 24 bits (23 explicitly stored)
    // Double significand precision: 53 bits (52 explicitly stored)
    printFloatIntPrecision(0);
    printDoubleIntPrecision(0);
    printDoubleLongPrecision(
        (long) Math.pow(2, 54)); // Start with 0 and it will take a long time. 2^53 is a big number.
  }
}
