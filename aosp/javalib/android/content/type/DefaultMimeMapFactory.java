package android.content.type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.function.Function;
import libcore.content.type.MimeMap;

public class DefaultMimeMapFactory {
  public static MimeMap create() {
    return create(new _$$Lambda$DefaultMimeMapFactory$3biOnZOOEGRoeaFehYke_eZnpCg(DefaultMimeMapFactory.class));
  }
  
  public static MimeMap create(Function<String, InputStream> paramFunction) {
    MimeMap.Builder builder = MimeMap.builder();
    parseTypes(builder, paramFunction, "debian.mime.types");
    parseTypes(builder, paramFunction, "android.mime.types");
    parseTypes(builder, paramFunction, "vendor.mime.types");
    return builder.build();
  }
  
  private static void parseTypes(MimeMap.Builder paramBuilder, Function<String, InputStream> paramFunction, String paramString) {
    try {
      InputStream inputStream = paramFunction.apply(paramString);
      Objects.requireNonNull(inputStream);
      inputStream = inputStream;
      try {
        BufferedReader bufferedReader = new BufferedReader();
        InputStreamReader inputStreamReader = new InputStreamReader();
        this(inputStream);
        this(inputStreamReader);
      } finally {
        if (inputStream != null)
          try {
            inputStream.close();
          } finally {
            inputStream = null;
          }  
      } 
    } catch (IOException|RuntimeException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to parse ");
      stringBuilder.append(paramString);
      throw new RuntimeException(stringBuilder.toString(), iOException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/type/DefaultMimeMapFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */