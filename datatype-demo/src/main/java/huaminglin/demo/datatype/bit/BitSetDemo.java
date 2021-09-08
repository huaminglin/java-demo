package huaminglin.demo.datatype.bit;

import java.util.BitSet;

public class BitSetDemo {

  public static void main(String[] args) {
    byte[] bytes = new byte[10];
    bytes[0] = 5;
    BitSet bitSet = BitSet.valueOf(bytes);
    for (int i = 0; i < bytes.length * 8; i++) {
      System.out.println(i + ": " + bitSet.get(i));
    }
  }
}
