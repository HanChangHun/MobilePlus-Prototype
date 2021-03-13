package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface TargetApi {
  int value();
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/TargetApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */