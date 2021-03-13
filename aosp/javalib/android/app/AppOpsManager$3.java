package android.app;

import com.android.internal.app.IAppOpsActiveCallback;
import java.util.concurrent.Executor;

class null extends IAppOpsActiveCallback.Stub {
  public void opActiveChanged(int paramInt1, int paramInt2, String paramString, boolean paramBoolean) {
    executor.execute(new _$$Lambda$AppOpsManager$3$aT8CbzI8Vm3cKKLkTbEyDBWuFQI(callback, paramInt1, paramInt2, paramString, paramBoolean));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */