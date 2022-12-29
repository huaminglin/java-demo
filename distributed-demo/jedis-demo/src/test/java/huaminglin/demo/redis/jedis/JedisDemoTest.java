package huaminglin.demo.redis.jedis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class JedisDemoTest {
    @Container
    public GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
            .withExposedPorts(6379);

    private JedisDemo underTest;

    @BeforeEach
    public void setUp() {
        underTest = new JedisDemo();
    }

    @Test
    public void testRunDemoInteger() {
        String value = underTest.runDemoInteger(redis.getHost(), redis.getFirstMappedPort());
        assertEquals("1", value);
    }
}
