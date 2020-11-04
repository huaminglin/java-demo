package huaminglin.demo.servlet.slash;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/*")
public class HelloWorldServlet extends HttpServlet {

  private static final long serialVersionUID = -1523667278394341308L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setHeader("Content-Type", "text/html");
    response.getWriter().write(request.getRequestURI());
    response.getWriter().write("\n<br/>");
    response.getWriter().write(request.getQueryString());
    response.getWriter().write("\n<br/>");
    response.getWriter().write(request.getParameter("p"));
  }
}
