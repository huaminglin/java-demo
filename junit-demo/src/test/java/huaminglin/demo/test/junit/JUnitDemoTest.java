package huaminglin.demo.test.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class JUnitDemoTest {

  @Test
  void lambdaExpressions() {
    List<Integer> numbers = Arrays.asList(1, 2, 3);
    assertTrue(numbers.stream()
        .mapToInt(i -> i)
        .sum() > 5, "Sum should be greater than 5");
  }
}
