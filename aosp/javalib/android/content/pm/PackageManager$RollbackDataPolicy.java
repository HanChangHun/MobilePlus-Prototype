package android.content.pm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface RollbackDataPolicy {
  public static final int RESTORE = 0;
  
  public static final int RETAIN = 2;
  
  public static final int WIPE = 1;
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageManager$RollbackDataPolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */