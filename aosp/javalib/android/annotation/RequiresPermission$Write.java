package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface Write {
  RequiresPermission value() default @RequiresPermission;
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/RequiresPermission$Write.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */