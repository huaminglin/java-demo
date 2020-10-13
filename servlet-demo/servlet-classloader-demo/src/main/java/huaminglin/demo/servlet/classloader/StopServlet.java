package huaminglin.demo.servlet.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/stop")
public class StopServlet extends HttpServlet {

    private void stopServer() {
        try {
            Class<?> aClass = Class
                .forName("org.apache.catalina.startup.Bootstrap", false, getClass().getClassLoader());
            Field daemonField = aClass.getDeclaredField("daemon");
            System.out.println("daemon.stopServer() from huaminglin.demo.servlet.classloader.StopServlet");
            daemonField.setAccessible(true);
            Object daemon = daemonField.get(null);
            Method stopServer = aClass.getDeclaredMethod("stopServer");
            stopServer.invoke(daemon);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        stopServer();
    }
}
