package android.app;

import android.compat.Compatibility;
import android.os.Process;
import com.android.internal.compat.ChangeReporter;
import java.util.Arrays;

public final class AppCompatCallbacks extends Compatibility.Callbacks {
  private final ChangeReporter mChangeReporter;
  
  private final long[] mDisabledChanges;
  
  private AppCompatCallbacks(long[] paramArrayOflong) {
    paramArrayOflong = Arrays.copyOf(paramArrayOflong, paramArrayOflong.length);
    this.mDisabledChanges = paramArrayOflong;
    Arrays.sort(paramArrayOflong);
    this.mChangeReporter = new ChangeReporter(1);
  }
  
  public static void install(long[] paramArrayOflong) {
    Compatibility.setCallbacks(new AppCompatCallbacks(paramArrayOflong));
  }
  
  private void reportChange(long paramLong, int paramInt) {
    int i = Process.myUid();
    this.mChangeReporter.reportChange(i, paramLong, paramInt);
  }
  
  protected boolean isChangeEnabled(long paramLong) {
    if (Arrays.binarySearch(this.mDisabledChanges, paramLong) < 0) {
      reportChange(paramLong, 1);
      return true;
    } 
    reportChange(paramLong, 2);
    return false;
  }
  
  protected void reportChange(long paramLong) {
    reportChange(paramLong, 3);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppCompatCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */