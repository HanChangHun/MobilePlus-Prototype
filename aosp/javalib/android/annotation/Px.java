package android.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE})
public @interface Px {}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/Px.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */