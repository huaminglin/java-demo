# aspectj demo

## Run the demo

mvn exec:java

```
String huaminglin.demo.aspectj.BusinessLogic.myMethod(String)
{[123]}
```

## Decompile the class file in the target/classes

```
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package huaminglin.demo.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.runtime.reflect.Factory;

public class BusinessLogic {
  public BusinessLogic() {
  }

  public String myMethod(String value) {
    JoinPoint var3 = Factory.makeJP(ajc$tjp_0, this, this, value);
    return (String)myMethod_aroundBody1$advice(this, value, var3, AOPLogic.aspectOf(), (ProceedingJoinPoint)var3, value);
  }

  static {
    ajc$preClinit();
  }
}

```