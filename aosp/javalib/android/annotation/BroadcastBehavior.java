package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
public @interface BroadcastBehavior {
  boolean explicitOnly() default false;
  
  boolean includeBackground() default false;
  
  boolean protectedBroadcast() default false;
  
  boolean registeredOnly() default false;
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/BroadcastBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */