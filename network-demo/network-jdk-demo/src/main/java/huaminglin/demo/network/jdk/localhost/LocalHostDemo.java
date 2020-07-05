package huaminglin.demo.network.jdk.localhost;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

public class LocalHostDemo  {
    public static String readString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream into = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        for (int n; 0 < (n = inputStream.read(buf));) {
            into.write(buf, 0, n);
        }
        into.close();
        return new String(into.toByteArray(), "UTF-8");
    }

    public static void main(String[] args) {
        {
            String hostname = null;
            try {
                hostname = java.net.InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println("Local host name: " + hostname);
        }
        {
            try {
                Process process = Runtime.getRuntime().exec("hostname");
                InputStream inputStream = process.getInputStream();
                String value = readString(inputStream);
                System.out.println("hostname: " + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("LocalHostDemo end.");
    }
}
