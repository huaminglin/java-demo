package huaminglin.demo.jdk.jmx.client;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class JMXClientDemo {
    public static void main(String[] args) throws IOException {
        String targetHost = System.getenv("TARGET_HOST");
        if (targetHost == null) {
            targetHost = "127.0.0.1";
        }
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + targetHost + ":9999/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        String domains[] = mbsc.getDomains();
        Arrays.sort(domains);
        for (String domain : domains) {
            System.out.println("\tDomain = " + domain);
        }
        System.out.println("\nMBeanServer default domain = " + mbsc.getDefaultDomain());

        System.out.println("\nMBean count = " +  mbsc.getMBeanCount());
        System.out.println("\nQuery MBeanServer MBeans:");
        Set<ObjectName> names =
                new TreeSet<>(mbsc.queryNames(null, null));
        for (ObjectName name : names) {
            System.out.println("\tObjectName = " + name);
        }
        jmxc.close();
    }
}

