package huaminglin.demo.jdk.net;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncoderDemo {
    public static void main(String[] args) {
        {
            String decodedUrl = "http://127.0.0.1:8080/a/b c/?d=e+f";
            System.out.println("Encode: " + decodedUrl);
            String encodedUrl = URLEncoder.encode(decodedUrl);
            System.out.println(encodedUrl);
            // http%3A%2F%2F127.0.0.1%3A8080%2Fa%2Fb+c%2F%3Fd%3De+f
        }
        {
            String decodedValue = "b c";
            System.out.println("Encode: " + decodedValue);
            String encodedValue = URLEncoder.encode(decodedValue);
            System.out.println(encodedValue);
            // b+c
        }
        {
            System.out.println("\nAfter \"decode -> encode\", the value is same as the original value:");
            String encodedValue = "b+c";
            System.out.println("Decode: " + encodedValue);
            String decodedValue = URLDecoder.decode(encodedValue);
            System.out.println(decodedValue);
            // b c
            encodedValue = URLEncoder.encode(decodedValue);
            System.out.println(encodedValue);
            // b+c
        }
        {
            System.out.println("\nAfter \"decode -> encode\", the value is different from the original value:");
            String encodedValue = "b%20c";
            System.out.println("Decode: " + encodedValue);
            String decodedValue = URLDecoder.decode(encodedValue);
            System.out.println(decodedValue);
            // b c
            encodedValue = URLEncoder.encode(decodedValue);
            System.out.println(encodedValue);
            // b+c
        }
    }
}
