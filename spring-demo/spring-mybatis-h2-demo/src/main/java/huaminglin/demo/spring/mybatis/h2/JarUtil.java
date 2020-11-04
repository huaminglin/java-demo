package huaminglin.demo.spring.mybatis.h2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;

public class JarUtil {

  public static String copyToTmpFolder(InputStream stream) throws IOException {
    File tempFile = File.createTempFile("demo-", ".tmp");
    String path = tempFile.getPath();
    Files.copy(stream, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
    return path;
  }

  public static String copyToTmpFolder(Resource resource) throws IOException {
    return copyToTmpFolder(resource.getInputStream());
  }
}
