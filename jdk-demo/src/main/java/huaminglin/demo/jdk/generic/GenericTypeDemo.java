package huaminglin.demo.jdk.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class GenericTypeDemo {
    public static class MyCallable implements Callable<Map<String, List<String>>> {

        @Override
        public Map<String, List<String>> call() throws Exception {
            return null;
        }
    }
    public static void printParameterizedType(ParameterizedType type) {
        System.out.println("Generic type: " + type);
        Type[] genericTypes = type.getActualTypeArguments();
        for (Type genericType : genericTypes) {
            if (genericType instanceof ParameterizedType) {
                printParameterizedType((ParameterizedType) genericType);
            } else {
                System.out.println("Type: " + genericType);
            }
        }

    }

    public static void main(String[] args) {
        Type[] types = MyCallable.class.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                printParameterizedType((ParameterizedType) type);
            } else {
                System.out.println("Type: " + type);
            }
        }
//        Generic type: java.util.concurrent.Callable<java.util.Map<java.lang.String, java.util.List<java.lang.String>>>
//        Generic type: java.util.Map<java.lang.String, java.util.List<java.lang.String>>
//        Type: class java.lang.String
//        Generic type: java.util.List<java.lang.String>
//        Type: class java.lang.String
    }
}
