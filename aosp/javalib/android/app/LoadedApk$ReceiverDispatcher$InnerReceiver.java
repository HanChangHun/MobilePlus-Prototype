package android.app;

import android.content.IIntentReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.lang.ref.WeakReference;

final class InnerReceiver extends IIntentReceiver.Stub {
  final WeakReference<LoadedApk.ReceiverDispatcher> mDispatcher;
  
  final LoadedApk.ReceiverDispatcher mStrongRef;
  
  InnerReceiver(LoadedApk.ReceiverDispatcher paramReceiverDispatcher, boolean paramBoolean) {
    this.mDispatcher = new WeakReference<>(paramReceiverDispatcher);
    if (!paramBoolean)
      paramReceiverDispatcher = null; 
    this.mStrongRef = paramReceiverDispatcher;
  }
  
  public void performReceive(Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    LoadedApk.ReceiverDispatcher receiverDispatcher;
    if (paramIntent == null) {
      Log.wtf("LoadedApk", "Null intent received");
      receiverDispatcher = null;
    } else {
      receiverDispatcher = this.mDispatcher.get();
    } 
    if (receiverDispatcher != null) {
      receiverDispatcher.performReceive(paramIntent, paramInt1, paramString, paramBundle, paramBoolean1, paramBoolean2, paramInt2);
    } else {
      IActivityManager iActivityManager = ActivityManager.getService();
      if (paramBundle != null)
        try {
          paramBundle.setAllowFds(false);
          iActivityManager.finishReceiver((IBinder)this, paramInt1, paramString, paramBundle, false, paramIntent.getFlags());
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      iActivityManager.finishReceiver((IBinder)this, paramInt1, paramString, paramBundle, false, remoteException.getFlags());
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$ReceiverDispatcher$InnerReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */