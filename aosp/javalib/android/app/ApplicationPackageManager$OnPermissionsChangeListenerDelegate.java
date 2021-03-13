package android.app;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.permission.IOnPermissionsChangeListener;

public class OnPermissionsChangeListenerDelegate extends IOnPermissionsChangeListener.Stub implements Handler.Callback {
  private static final int MSG_PERMISSIONS_CHANGED = 1;
  
  private final Handler mHandler;
  
  private final PackageManager.OnPermissionsChangedListener mListener;
  
  public OnPermissionsChangeListenerDelegate(PackageManager.OnPermissionsChangedListener paramOnPermissionsChangedListener, Looper paramLooper) {
    this.mListener = paramOnPermissionsChangedListener;
    this.mHandler = new Handler(paramLooper, this);
  }
  
  public boolean handleMessage(Message paramMessage) {
    if (paramMessage.what != 1)
      return false; 
    int i = paramMessage.arg1;
    this.mListener.onPermissionsChanged(i);
    return true;
  }
  
  public void onPermissionsChanged(int paramInt) {
    this.mHandler.obtainMessage(1, paramInt, 0).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationPackageManager$OnPermissionsChangeListenerDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */