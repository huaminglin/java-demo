package huaminglin.demo.network.jdk.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Security;
import java.util.Arrays;
import java.util.stream.IntStream;

public class DnsDemo {

  private static void printIpAddress(String hostname) {
    long start = System.nanoTime();
    InetAddress[] addresses = new InetAddress[0];
    try {
      addresses = InetAddress.getAllByName(hostname);
    } catch (UnknownHostException e) {
      System.out.println("Unknown host: " + hostname + ", time: " + (System.nanoTime() - start));
//      e.printStackTrace();
    }
    long duration = System.nanoTime() - start;
    Arrays.stream(addresses)
        .forEach(address -> System.out.println(address.toString() + ", time: " + duration));
  }

  public static void main(String[] args) {
    int count = 1;
    int sleep = 10;
    String hostname = "server01";
    if (args.length > 0) {
      count = Integer.parseInt(args[0]);
    }
    if (args.length > 1) {
      sleep = Integer.parseInt(args[1]);
    }
    if (args.length > 2) {
      hostname = args[2];
    }

    if (args.length > 3) {
      Security.setProperty("networkaddress.cache.ttl", args[3]);
    }

    if (args.length > 4) {
      Security.setProperty("networkaddress.cache.negative.ttl", args[4]);
    }

    int finalSleep = sleep;
    String finalHostname = hostname;
    IntStream.range(0, count).forEach(i -> {
      if (i > 0) {
        try {
          Thread.sleep(finalSleep);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      printIpAddress(finalHostname);
    });
  }
}
