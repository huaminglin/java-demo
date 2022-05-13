package huaminglin.demo.bytebuddy;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

public final class ByteBuddyAdviceDemo {
  static Class myAdviceClass;
  static Instrumentation myInstrumentation;

  private static Class modifyAdviceClass() {
    Class adviceClass = new ByteBuddy()
        .redefine(HelloAdvice.class)
        .name("huaminglin.demo.bytebuddy.NewAdviceClass")
//        .field(ElementMatchers.named("name"))
//        .value("newName")
        .make()
        .load(ByteBuddyAdviceDemo.class.getClassLoader())
        .getLoaded();
    return adviceClass;
  }

  public static void premain(String arguments, Instrumentation instrumentation) {
    myInstrumentation = instrumentation;
    Class adviceClass = modifyAdviceClass();
    myAdviceClass = adviceClass;
    // For some reason, the dynamic class can't be used as advice class.
    // Maybe ByteBuddy reads the class file from the file system, a dynamic class doesn't exist in the file system.
    //
    // net.bytebuddy.asm.Advice.to(net.bytebuddy.description.type.TypeDescription, net.bytebuddy.asm.Advice.PostProcessor.Factory, net.bytebuddy.dynamic.ClassFileLocator, java.util.List<? extends net.bytebuddy.asm.Advice.OffsetMapping.Factory<?>>, net.bytebuddy.asm.Advice.Delegator)
    // ClassReader classReader = methodEnter.isBinary() || methodExit.isBinary() ? OpenedClassReader.of(classFileLocator.locate(advice.getName()).resolve()) : UNDEFINED;
    //
    // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.locate
    // ExtractionClassFileTransformer classFileTransformer = new ExtractionClassFileTransformer(classLoadingDelegate.getClassLoader(), name);
    //
    // TODO: Save the dynamic class to file system and load it with a customized class loader.
    // Block: class dump is not possible in premain() call.
    //
    // ByteBuddy documentation:
    // For being able to locate the advice code in the context of the library dependencies, Byte Buddy offers an AgentBuilder.Transformer.ForAdvice implementation that allows registering the agent's class file locators for assembly of the advice class's description at runtime and with respect to the specific user dependencies.

//    ClassDumper.dump(instrumentation, Paths.get("/tmp/NewAdviceClass.class"), adviceClass);
    // Caused by: java.lang.UnsupportedOperationException: adding retransformable transformers is not supported in this environment
    // class dump fails on premain()

//    Class adviceClass = HelloAdvice.class;
//    myAdviceClass = adviceClass;

    new AgentBuilder.Default()
        .type(ElementMatchers.named("huaminglin.demo.bytebuddy.MyExistingClass"))
        .transform((builder, typeDescription, classLoader, module) -> builder
            .method(ElementMatchers.nameContainsIgnoreCase("hello"))
            .intercept(Advice.to(adviceClass)))
        .installOn(instrumentation);
  }

  public static void main(String[] args)
      throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    MyExistingClass.hello();
    if (myAdviceClass != null) {
      myAdviceClass.getDeclaredMethod("hello").invoke(null);
      System.out.println(myAdviceClass.getProtectionDomain().getCodeSource()); // (null <no signer certificates>)
      ClassDumper.dump(myInstrumentation, Paths.get("/tmp/NewAdviceClass.class"), myAdviceClass);
    }
  }
}
