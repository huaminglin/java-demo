package huaminglin.demo.bytebuddy;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.ProtectionDomain;
import java.util.function.Predicate;

public final class ClassDumper {
  private static class UtilTransformer implements ClassFileTransformer {
    byte[] classFile;
    Predicate<Class> targetClass;

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
        ProtectionDomain pd, byte[] classFile) {
      System.out.println("transform: " + className);
      System.out.println("transform: " + classBeingRedefined);
      if (classBeingRedefined != null && targetClass.test(classBeingRedefined)) {
        this.classFile = classFile;
      }
      return null;
    }
  }

  public static void dump(Instrumentation inst, Path outputFilePath, Class targetClass)  {
    UtilTransformer transformer = new UtilTransformer();
    transformer.targetClass = clazz ->  clazz.equals(targetClass);

    inst.addTransformer(transformer, true);
    try {
      inst.retransformClasses(targetClass);
    } catch (Throwable e) {
      e.printStackTrace();
    }
    inst.removeTransformer(transformer);
    try {
      Files.write(outputFilePath, transformer.classFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
