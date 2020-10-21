package huaminglin.demo.jvm.gc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class GcDemo {
    private static ArrayList<byte[]> values = new ArrayList<>();

    private static byte[] allocateBytes(Random rd, int size) {
        byte[] result = new byte[size];
        rd.nextBytes(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        Random rd = new Random();
        Map<String, String> menus = new LinkedHashMap<>();
        menus.put("1", "1M byte array");
        menus.put("2", "10M byte array");
        menus.put("3", "100M byte array");
        menus.put("9", "Release byte arrays");
        menus.put("0", "quit");
        menus.forEach((k, v) -> System.out.println(k + " for " + v));
        System.out.println("Please input your choice: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String choice = reader.readLine();
            if ("1".equals(choice)) {
                values.add(allocateBytes(rd, 1024 * 1024));
            } else if ("2".equals(choice)) {
                values.add(allocateBytes(rd, 1024 * 1024 * 10));
            } else if ("3".equals(choice)) {
                values.add(allocateBytes(rd, 1024 * 1024 * 10 * 10));
            } else if ("9".equals(choice)) {
                values = new ArrayList<>();
            } else {
                break;
            }
            if (menus.containsKey(choice)) {
                System.out.println(menus.get(choice));
            }
        }
    }
}
