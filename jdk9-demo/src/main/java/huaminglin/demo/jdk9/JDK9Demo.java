package huaminglin.demo.jdk9;

import jdk.incubator.http.HttpClient;

public class JDK9Demo {

  public static HttpClient getClient() {
    return HttpClient.newHttpClient();
  }

  public static void main(String[] args) {
  }
}
