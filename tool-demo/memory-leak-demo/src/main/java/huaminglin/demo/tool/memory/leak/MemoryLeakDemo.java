package huaminglin.demo.tool.memory.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class MemoryLeakDemo {

  public static void main(String[] args) throws InterruptedException, IOException {
    MemoryLeakDemo replay = new MemoryLeakDemo();
    List<HttpUriRequest> cache = replay.loadMockRequest(100000, "http://127.0.0.1:8080/");
    replay.start(cache, 10);
  }

  public List<HttpUriRequest> loadMockRequest(int n, String url) {
    List<HttpUriRequest> cache = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      HttpGet request = new HttpGet(url + "?a=" + i);
      cache.add(request);
    }
    return cache;
  }

  public void start(List<HttpUriRequest> cache, int interval)
      throws InterruptedException, IOException {
    CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
    httpClient.start();
    int i = 0;
    while (i < cache.size()) {
      final HttpUriRequest request = cache.get(i % cache.size());
      httpClient.execute(request, new FutureCallback<HttpResponse>() {
        public void completed(final HttpResponse response) {
          System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
        }

        public void failed(final Exception ex) {
          System.out.println(request.getRequestLine() + "->" + ex);
        }

        public void cancelled() {
          System.out.println(request.getRequestLine() + " cancelled");
        }

      });
      i++;
      Thread.sleep(interval);
    }
    httpClient.close();
  }
}
