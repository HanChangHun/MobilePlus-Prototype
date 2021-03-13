package android.app;

import android.content.pm.IPackageMoveObserver;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.internal.os.SomeArgs;

class MoveCallbackDelegate extends IPackageMoveObserver.Stub implements Handler.Callback {
  private static final int MSG_CREATED = 1;
  
  private static final int MSG_STATUS_CHANGED = 2;
  
  final PackageManager.MoveCallback mCallback;
  
  final Handler mHandler;
  
  public MoveCallbackDelegate(PackageManager.MoveCallback paramMoveCallback, Looper paramLooper) {
    this.mCallback = paramMoveCallback;
    this.mHandler = new Handler(paramLooper, this);
  }
  
  public boolean handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2)
        return false; 
      someArgs = (SomeArgs)paramMessage.obj;
      this.mCallback.onStatusChanged(someArgs.argi1, someArgs.argi2, ((Long)someArgs.arg3).longValue());
      someArgs.recycle();
      return true;
    } 
    SomeArgs someArgs = (SomeArgs)((Message)someArgs).obj;
    this.mCallback.onCreated(someArgs.argi1, (Bundle)someArgs.arg2);
    someArgs.recycle();
    return true;
  }
  
  public void onCreated(int paramInt, Bundle paramBundle) {
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.argi1 = paramInt;
    someArgs.arg2 = paramBundle;
    this.mHandler.obtainMessage(1, someArgs).sendToTarget();
  }
  
  public void onStatusChanged(int paramInt1, int paramInt2, long paramLong) {
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.argi1 = paramInt1;
    someArgs.argi2 = paramInt2;
    someArgs.arg3 = Long.valueOf(paramLong);
    this.mHandler.obtainMessage(2, someArgs).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationPackageManager$MoveCallbackDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */