package huaminglin.demo.jvm.constant.pool;

public class StringConstantPoolDemo {
    static String staticString1 = "huaminglin.demo.jvm.constant.pool.StringConstantPoolDemo.staticString1";

    private static boolean isInternString(String value) {
        return value == value.intern();
    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        System.out.println("StringConstantPoolDemo.class.getName() is intern string: " + isInternString(StringConstantPoolDemo.class.getName()));

        int sleep1 = 60;
        int sleep2 = 60;
        if (args.length > 0) {
            sleep1 = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            sleep2 = Integer.parseInt(args[1]);
        }
        {
            Class.forName("huaminglin.demo.jvm.constant.pool.StaticStringFieldClass", false, ClassLoader.getSystemClassLoader());
            System.out.println("Check heap dump about StaticStringFieldClass. First sleep: " + sleep1);
            Thread.sleep(1000 * sleep1);

            Class.forName("huaminglin.demo.jvm.constant.pool.StaticStringFieldClass", true, ClassLoader.getSystemClassLoader());
            System.out.println("Check heap dump about StaticStringFieldClass. Second Second: " + sleep2);
            Thread.sleep(1000 * sleep2);
        }
    }
}
