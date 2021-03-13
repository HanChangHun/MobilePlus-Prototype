package android.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
public @interface DataBucketKey {}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$DataBucketKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */