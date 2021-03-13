package android.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
public @interface Dimension {
  public static final int DP = 0;
  
  public static final int PX = 1;
  
  public static final int SP = 2;
  
  int unit() default 1;
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Unit {}
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/Dimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */