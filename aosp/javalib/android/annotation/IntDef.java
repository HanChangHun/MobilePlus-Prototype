package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.ANNOTATION_TYPE})
public @interface IntDef {
  boolean flag() default false;
  
  String[] prefix() default {};
  
  String[] suffix() default {};
  
  int[] value() default {};
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/IntDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */