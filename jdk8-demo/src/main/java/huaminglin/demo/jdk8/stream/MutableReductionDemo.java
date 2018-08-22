package huaminglin.demo.jdk8.stream;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MutableReductionDemo {
    private static Map<String, String[]> accumulateEntry(Map<String, String[]> map, Map.Entry<String, String> entry) {
        map.put(entry.getKey(), new String[] {entry.getValue()});
        return map;
    }

    public static Map<String, String[]> collect(Stream<Map.Entry<String, String>> stream) {
        // It seems that the combiner doesn't affect the result.
        // (a, b) -> null should be "(a, b) -> a.putAll(b)"
        Map<String, String[]> newMap = stream.collect(
                HashMap::new,
                MutableReductionDemo::accumulateEntry,
                (a, b) -> {}
        );
        return newMap;
    }

    private static String[] valueMapper(Map.Entry<String, String> entry) {
        return new String[] {entry.getValue()};
    }

    public static Map<String, String[]> collector(Stream<Map.Entry<String, String>> stream) {
        // It seems that the mergeFunction doesn't affect the result.
        // (a, b) -> null should be "(a, b) -> a.putAll(b)"
        Map<String, String[]> newMap = stream.collect(
                Collectors.toMap(Map.Entry::getKey, MutableReductionDemo::valueMapper,
                        (a, b) -> a, HashMap::new)
        );
        return newMap;
    }

    public static void main(String[] args) {
        Map<String, String> mapA = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            mapA.put("key" + i, "value" + i);
        }
        {
            Map<String, String[]> newMap = collect(mapA.entrySet().stream());
            System.out.println(newMap.size());
        }
        {
            Map<String, String[]> newMap = collector(mapA.entrySet().stream());
            System.out.println(newMap.size());
        }
        {
            Map<String, String[]> newMap = collect(mapA.entrySet().parallelStream());
            System.out.println(newMap.size());
            if (newMap.size() < mapA.size()) {
                System.out.println("A correct combiner is required for parallel reduction.");
            }
        }
        {
            Map<String, String[]> newMap = collector(mapA.entrySet().parallelStream());
            System.out.println(newMap.size());
            if (newMap.size() < mapA.size()) {
                System.out.println("A correct combiner is required for parallel reduction.");
            } else {
                System.out.println("Warning: parallel reduction even works with a broken combiner.");
            }
        }
//        1000000
//        1000000
//        57915
//        1000000
    }
}
