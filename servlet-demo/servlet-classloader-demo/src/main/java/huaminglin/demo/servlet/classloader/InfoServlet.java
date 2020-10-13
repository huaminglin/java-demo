package huaminglin.demo.servlet.classloader;

import java.io.IOException;
import java.net.URLClassLoader;
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {

    private static String getClassLoaderHierarchy(ClassLoader loader) {
        StringBuilder builder = new StringBuilder();
        while (loader != null) {
            builder.append('\n').append('\n').append(loader);
            if (loader instanceof URLClassLoader) {
                builder.append("\nName: ").append(loader.getName());
                Arrays.stream(((URLClassLoader) loader).getURLs()).forEach(url -> builder.append('\n').append(url));
            }
            loader = loader.getParent();
        }
        return builder.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().write("this.getClass().getClassLoader(): " + getClassLoaderHierarchy(getClass().getClassLoader()));
    }
}
