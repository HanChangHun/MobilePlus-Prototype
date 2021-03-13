package android.app;

import android.content.ComponentName;
import android.os.IBinder;

final class DeathMonitor implements IBinder.DeathRecipient {
  final ComponentName mName;
  
  final IBinder mService;
  
  DeathMonitor(ComponentName paramComponentName, IBinder paramIBinder) {
    this.mName = paramComponentName;
    this.mService = paramIBinder;
  }
  
  public void binderDied() {
    LoadedApk.ServiceDispatcher.this.death(this.mName, this.mService);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$ServiceDispatcher$DeathMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */