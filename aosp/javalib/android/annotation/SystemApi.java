package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(SystemApi.Container.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE, ElementType.PACKAGE})
public @interface SystemApi {
  Client client() default Client.PRIVILEGED_APPS;
  
  public enum Client {
    MODULE_LIBRARIES, PRIVILEGED_APPS, SYSTEM_SERVER;
    
    static {
      Client client = new Client("SYSTEM_SERVER", 2);
      SYSTEM_SERVER = client;
      $VALUES = new Client[] { PRIVILEGED_APPS, MODULE_LIBRARIES, client };
    }
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE})
  public static @interface Container {
    SystemApi[] value();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/SystemApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */