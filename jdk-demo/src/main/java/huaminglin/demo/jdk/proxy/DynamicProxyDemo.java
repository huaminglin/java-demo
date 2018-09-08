package huaminglin.demo.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static interface MyInterfaceA {
        void printFirstMessageA();
        void printSecondMessageA();
    }

    public static interface MyInterfaceB {
        void printFirstMessageB();
        void printSecondMessageB();
    }

    public static class MyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(proxy.getClass() + "::" + method.getName());
            return 123;
		}

    }
    
    public static void main(String[] args) {
        Object obj = Proxy.newProxyInstance(DynamicProxyDemo.class.getClassLoader(),
         new Class[] {MyInterfaceA.class, MyInterfaceB.class },
         new MyInvocationHandler()
         );
         ((MyInterfaceA) obj).printFirstMessageA();
         ((MyInterfaceA) obj).printSecondMessageA();
         ((MyInterfaceB) obj).printFirstMessageB();
         ((MyInterfaceB) obj).printSecondMessageB();
    }
}
