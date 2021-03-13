package android.app;

import com.android.internal.app.IAppOpsStartedCallback;

class null extends IAppOpsStartedCallback.Stub {
  public void opStarted(int paramInt1, int paramInt2, String paramString, int paramInt3) {
    callback.onOpStarted(paramInt1, paramInt2, paramString, paramInt3);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */