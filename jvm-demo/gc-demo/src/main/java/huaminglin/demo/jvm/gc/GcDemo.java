package huaminglin.demo.jvm.gc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class GcDemo {
    private static ArrayList<byte[]> values01 = new ArrayList<>();
    private static ArrayList<byte[]> values02 = new ArrayList<>();

    private static ArrayList<byte[]> allocateBytes(Random rd, int size, int blockSize) {
        ArrayList<byte[]> result = new ArrayList<>();
        if (blockSize <= 0) {
            blockSize = size;
        }
        blockSize = Math.min(blockSize, size);
        for (int added = 0; added < size; added += blockSize) {
            byte[] block = new byte[blockSize];
            rd.nextBytes(block);
            result.add(block);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Random rd = new Random();
        Map<String, String> menus = new LinkedHashMap<>();
        menus.put("1", "1M byte array to values01");
        menus.put("2", "10M byte array to values01");
        menus.put("3", "100M byte array to values01");
        menus.put("4", "1M byte array to values02");
        menus.put("5", "10M byte array to values02");
        menus.put("6", "100M byte array to values02");
        menus.put("8", "Release byte arrays 01");
        menus.put("9", "Release byte arrays 02");
        menus.put("0", "quit");
        menus.forEach((k, v) -> System.out.println(k + " for " + v));
        System.out.println("Please input your choice: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int blockSize = 1024 * 1024 * 10; // Use G1 Humongous Allocation
        int blockSize = 1024; // Not use G1 Humongous Allocation
        while (true) {
            String choice = reader.readLine();
            if ("1".equals(choice)) {
                values01.addAll(allocateBytes(rd, 1024 * 1024, blockSize));
            } else if ("2".equals(choice)) {
                values01.addAll(allocateBytes(rd, 1024 * 1024 * 10, blockSize));
            } else if ("3".equals(choice)) {
                values01.addAll(allocateBytes(rd, 1024 * 1024 * 10 * 10, blockSize));
            } else if ("4".equals(choice)) {
                values02.addAll(allocateBytes(rd, 1024 * 1024, blockSize));
            } else if ("5".equals(choice)) {
                values02.addAll(allocateBytes(rd, 1024 * 1024 * 10, blockSize));
            } else if ("6".equals(choice)) {
                values02.addAll(allocateBytes(rd, 1024 * 1024 * 10 * 10, blockSize));
            } else if ("8".equals(choice)) {
                values01 = new ArrayList<>();
            } else if ("9".equals(choice)) {
                values02 = new ArrayList<>();
            } else {
                break;
            }
            if (menus.containsKey(choice)) {
                System.out.println(menus.get(choice));
            }
        }
    }
}
