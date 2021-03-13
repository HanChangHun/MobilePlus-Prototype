package android.app;

import android.content.ComponentName;
import android.os.IBinder;

final class RunConnection implements Runnable {
  final int mCommand;
  
  final boolean mDead;
  
  final ComponentName mName;
  
  final IBinder mService;
  
  RunConnection(ComponentName paramComponentName, IBinder paramIBinder, int paramInt, boolean paramBoolean) {
    this.mName = paramComponentName;
    this.mService = paramIBinder;
    this.mCommand = paramInt;
    this.mDead = paramBoolean;
  }
  
  public void run() {
    int i = this.mCommand;
    if (i == 0) {
      LoadedApk.ServiceDispatcher.this.doConnected(this.mName, this.mService, this.mDead);
    } else if (i == 1) {
      LoadedApk.ServiceDispatcher.this.doDeath(this.mName, this.mService);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$ServiceDispatcher$RunConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */