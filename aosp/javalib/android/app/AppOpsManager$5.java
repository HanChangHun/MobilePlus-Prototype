package android.app;

import com.android.internal.app.IAppOpsNotedCallback;

class null extends IAppOpsNotedCallback.Stub {
  public void opNoted(int paramInt1, int paramInt2, String paramString, int paramInt3) {
    callback.onOpNoted(paramInt1, paramInt2, paramString, paramInt3);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */