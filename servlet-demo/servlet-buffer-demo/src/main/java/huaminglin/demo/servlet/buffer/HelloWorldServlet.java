package huaminglin.demo.servlet.buffer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloWorld")
public class HelloWorldServlet extends HttpServlet {

  private static final long serialVersionUID = -1523667278394341308L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String queryString = request.getQueryString();
    boolean lengthSetted = queryString != null && queryString.contains("length");
    boolean flushSetted = queryString != null && queryString.contains("flush");
    String strCount = request.getParameter("count");
    if (strCount == null) {
      strCount = "10";
    }
    int count = Integer.parseInt(strCount);
    StringBuilder content = new StringBuilder();
    for (int i = 0; i < count; i++) {
      content.append("a");
    }
    response.setHeader("Content-Type",
        "text/html"); // So browser can show the content as soon as it receive it.
    if (lengthSetted) {
      response.setHeader("Content-Length", content.length() + "");
    } else {
      // Tomcat container will set "Transfer-Encoding:chunked"
    }
    for (int i = 0; i < content.length(); i++) {
      response.getWriter().write(content.substring(i, i + 1));
      if (flushSetted) {
        response.getWriter().flush();
      }
    }
  }
}
