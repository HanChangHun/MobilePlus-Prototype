package android.filterfw.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface GenerateFieldPort {
  boolean hasDefault() default false;
  
  String name() default "";
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/GenerateFieldPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */