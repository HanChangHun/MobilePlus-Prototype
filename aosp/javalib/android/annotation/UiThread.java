package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE, ElementType.PARAMETER})
public @interface UiThread {}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/UiThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */