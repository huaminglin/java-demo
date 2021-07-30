package huaminglin.demo.datatype.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OgnlDemo {
  static class Context {
    Object[] arguments = {"abc", new Object()};
  }

  public static void main(String[] args) throws OgnlException {
    Context context = new Context();
    OgnlContext ognlContext = new OgnlContext(new DefaultMemberAccess(true), null, null, null);
    ognlContext.put("context", context);
    ognlContext.setRoot(context);
    String express = "(#context.arguments) ? ((#context.arguments.length > 0) ? #context.arguments[0].getClass().getName():123) : 123";
    Object obj1 = Ognl.parseExpression(express);
    Object result = Ognl.getValue(obj1, ognlContext, ognlContext.getRoot());
    System.out.println(result);
  }
}
