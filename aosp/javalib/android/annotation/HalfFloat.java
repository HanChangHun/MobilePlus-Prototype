package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.FIELD})
public @interface HalfFloat {}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/HalfFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */