package android.app;

import android.os.IRemoteCallback;
import android.os.RemoteException;

public abstract class SynchronousUserSwitchObserver extends UserSwitchObserver {
  public abstract void onUserSwitching(int paramInt) throws RemoteException;
  
  public final void onUserSwitching(int paramInt, IRemoteCallback paramIRemoteCallback) throws RemoteException {
    try {
      onUserSwitching(paramInt);
      return;
    } finally {
      if (paramIRemoteCallback != null)
        paramIRemoteCallback.sendResult(null); 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SynchronousUserSwitchObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */