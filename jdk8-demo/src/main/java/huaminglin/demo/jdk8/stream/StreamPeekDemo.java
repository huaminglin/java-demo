package huaminglin.demo.jdk8.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPeekDemo {
    public static void main(String[] args) {
        List<String> strings = Stream.of("one", "two", "three", "four")
                .peek(e -> System.out.println("Original Element: " + e))
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println("Final Results: " + strings);
        /* Output:
Original Element: one
Original Element: two
Original Element: three
Filtered value: three
Mapped value: THREE
Original Element: four
Filtered value: four
Mapped value: FOUR
Final Results: [THREE, FOUR]
         */
    }
}
