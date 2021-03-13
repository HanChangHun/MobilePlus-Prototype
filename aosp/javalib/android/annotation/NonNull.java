package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
public @interface NonNull {}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/NonNull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */