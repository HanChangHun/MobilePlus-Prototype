package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PACKAGE})
public @interface UserHandleAware {}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/UserHandleAware.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */